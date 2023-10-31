package com.smit.Backend.Filters;

import java.io.IOException;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import com.smit.Backend.Execptions.BadRequestException;
import com.smit.Backend.Helpers.JWTHelper;
import com.smit.Backend.Repositories.Interfaces.IUserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TokenFilter extends OncePerRequestFilter {
    private JWTHelper jwtHelper;
    private IUserRepository userRepository;

    @Autowired
    public TokenFilter(JWTHelper jwtHelper, IUserRepository userRepository) {
        this.jwtHelper = jwtHelper;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: token not found");
            return;
        }

        // verify the token
        var tokenData = jwtHelper.validateToken(token);
        if (tokenData == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized User");
            return;
        }

        // check user exist or not
        var userId = tokenData.getClaim("userId").toString();
        var user = userRepository.findById(userId.substring(1, userId.length() - 1)).orElseThrow(
                () -> new BadRequestException("User with id " + userId + " does not exist."));

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        if (path.equals("/api/authentication/login") || path.equals("/api/authentication/register")) {
            return true;
        }
        if (path.equals("/api/files/") && request.getMethod().equals("GET")) {
            return false;
        } else if (path.startsWith("/api/files") && request.getMethod().equals("GET")) {
            return true;
        }
        return false;
    }
}