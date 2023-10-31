package com.smit.Backend.Filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import com.smit.Backend.Execptions.BadRequestException;
import com.smit.Backend.Helpers.JWTHelper;
import com.smit.Backend.Repositories.Interfaces.IUserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Token filter class.
 */
public class TokenFilter extends OncePerRequestFilter {
    private JWTHelper jwtHelper;
    private IUserRepository userRepository;

    /**
     * Constructor.
     * 
     * @param jwtHelper      JWT helper
     * @param userRepository user repository
     */
    @Autowired
    public TokenFilter(JWTHelper jwtHelper, IUserRepository userRepository) {
        this.jwtHelper = jwtHelper;
        this.userRepository = userRepository;
    }

    /**
     * This method filters requests.
     * 
     * @param request     HTTP request
     * @param response    HTTP response
     * @param filterChain filter chain
     * @throws ServletException servlet exception
     * @throws IOException      IO exception
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // get token from header
        String token = request.getHeader("Authorization");
        if (token == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: token not found");
            return;
        }

        // validate token
        var tokenData = jwtHelper.validateToken(token);
        if (tokenData == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized User");
            return;
        }

        // check if user exists
        var userId = tokenData.getClaim("userId").toString();
        userRepository.findById(userId.substring(1, userId.length() - 1)).orElseThrow(
                () -> new BadRequestException("User with id " + userId + " does not exist."));

        filterChain.doFilter(request, response);
    }

    /**
     * This method checks if the request should not be filtered.
     * 
     * @param request HTTP request
     * @return true if the request should not be filtered, false otherwise
     * @throws ServletException servlet exception
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // ignore paths that do not need jwt authentication
        String path = request.getServletPath();
        if (path.equals("/api/authentication/login") || path.equals("/api/authentication/register")) {
            return true;
        } else if (path.equals("/api/files/") && request.getMethod().equals("GET")) {
            return false;
        } else if (path.startsWith("/api/files") && request.getMethod().equals("GET")) {
            return true;
        }
        return false;
    }
}