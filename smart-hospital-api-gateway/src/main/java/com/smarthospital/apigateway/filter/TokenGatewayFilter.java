package com.smarthospital.apigateway.filter;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.security.Key;
import io.jsonwebtoken.security.Keys;
/**
 * Implement this feature later
 */
//
//
//@Component
//public class TokenGatewayFilter implements GatewayFilter {
//
//    private static final String SECRET =
//            "mySuperSecretKeyForJwtGeneration123456";
//
//    private static final Key KEY =
//            Keys.hmacShaKeyFor(SECRET.getBytes());
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        String path = exchange.getRequest().getURI().getPath();
//
//        if (path.contains("/auth-service/auth/login") ||
//                path.contains("/auth-service/auth/register")) {
//            return chain.filter(exchange);
//        }
//        String authHeader = exchange.getRequest()
//                .getHeaders()
//                .getFirst(HttpHeaders.AUTHORIZATION);
//
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            return unauthorized(exchange);
//        }
//
//        String token = authHeader.substring(7);
//
//        if (!validateToken(token)) {
//            return unauthorized(exchange);
//        }
//
//        return chain.filter(exchange);
//    }
//
//    private Mono<Void> unauthorized(ServerWebExchange exchange) {
//        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//        return exchange.getResponse().setComplete();
//    }
//    public static boolean validateToken(String token) {
//
//        try {
//            Jwts.parserBuilder()
//                    .setSigningKey(KEY)
//                    .build()
//                    .parseClaimsJws(token);
//
//            return true;
//
//        } catch (JwtException e) {
//            return false;
//        }
//    }
//}
