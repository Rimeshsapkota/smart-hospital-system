package com.smarthospital.authservice.auth.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

/**
 * This class is used to generate and validate JWT token
 *
 * @author rimesh
 * @version 1.0
 */

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${jwt.secret}")
    private String secret;

    private Key getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    @Value("${jwt.expiration}")
    private long expiration;

    @Override
    public String extractUserName(String token) {
        return extractClaims(token).getSubject();
    }
    /**
     * Generates a JWT (JSON Web Token) based on the provided UserDetails.
     *
     * @param userDetails The UserDetails containing information about the user.
     * @return The generated JWT.
     */
    @Override
    public String generateToken(UserDetails userDetails) {
        CustomUserDetails user = (CustomUserDetails) userDetails;
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("role",user.getRole())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(getKey(),SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        return userDetails.getUsername().equals(extractUserName(token)) && isTokenExpired(token);
    }

    /**
     * Checks if the expiration date of a given JWT (JSON Web Token) is in the future,
     * indicating that the token is not yet expired.
     *
     * @param token The JWT to check for expiration.
     * @return {@code true} if the token is not expired, {@code false} otherwise.
     */
    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().after(new Date());
    }

    /**
     * Extracts the claims (payload) from a given JWT (JSON Web Token).
     *
     * @param token The JWT from which to extract claims.
     * @return A Claims object representing the payload of the JWT.
     */
    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
