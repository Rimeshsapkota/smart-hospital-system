package com.smarthospital.apigateway.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class ApiGateWayJwtUtils {

    private static final String SECRET =
            "mySuperSecretKeyForJwtGeneration123456";

    private static final Key KEY =
            Keys.hmacShaKeyFor(SECRET.getBytes());
    public void validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(KEY) // same KEY used during generation
                    .build()
                    .parseClaimsJws(token);

        } catch (Exception ignored) {
        }
    }
}
