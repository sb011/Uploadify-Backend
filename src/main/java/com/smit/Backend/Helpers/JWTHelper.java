package com.smit.Backend.Helpers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JWTHelper {
    @Value("${jwt.secret}")
    private String secretKey;

    public String generateToken(String userId) {
        String token = JWT.create().withClaim("userId", userId)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .sign(Algorithm.HMAC256(secretKey.getBytes()));

        return token;
    }

    public DecodedJWT validateToken(String token) {
        if (secretKey == null) {
            System.out.println(secretKey);
            return null;
        }
        DecodedJWT response = JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token);
        return response;
    }
}
