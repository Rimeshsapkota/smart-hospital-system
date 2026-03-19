package com.smarthospital.authservice.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements WebFilter {

    private final JwtService jwtService;
    private final UserDetailService userDetailService;

    private final List<String> openEndpoints = List.of(
            "/api/user/signup",
            "/api/user/signin",
            "/v3/api-docs/**",
            "/v3/api-docs",
            "/swagger-ui.html",
            "/webjars/",
            "/webjars/swagger-ui/index.html",
            "/favicon.ico",
            "/v3/api-docs",
            "/auth-service/v3/api-docs",
            "/patient-service/v3/api/docs"
    );
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        if (openEndpoints.stream().anyMatch(path::startsWith)) {
            return chain.filter(exchange); // skip public endpoints
        }
        final String authHeader = exchange.getRequest()
                .getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return unauthorized(exchange);
        }

        String jwtToken = authHeader.substring(7);
        String username;
        try {
            username = jwtService.extractUserName(jwtToken);
        } catch (Exception e) {
            return unauthorized(exchange);
        }

        return userDetailService.findByUsername(username)
                .flatMap(userDetails -> {
                    if (jwtService.isTokenValid(jwtToken, userDetails)) {
                        UsernamePasswordAuthenticationToken auth =
                                new UsernamePasswordAuthenticationToken(
                                        userDetails,
                                        null,
                                        userDetails.getAuthorities()// use authorities from userDetails
                                );
                        System.out.println(userDetails.getAuthorities());
                        return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(auth));
                    } else {
                        return unauthorized(exchange);
                    }
                });
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}