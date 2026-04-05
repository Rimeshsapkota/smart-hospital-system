package com.smarthospital.apigateway.filter;

import io.jsonwebtoken.Claims;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;


@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private ApiGateWayJwtUtils jwtUtils;

    @Autowired
    private RouterValidator routerValidator;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Autowired
    private AuthorizationFilter authorizationFilter;

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

            if (routerValidator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    throw new RuntimeException("Missing Authorization Header");
                }

                String authHeader = exchange.getRequest()
                        .getHeaders()
                        .getFirst(HttpHeaders.AUTHORIZATION);

                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    String token = authHeader.substring(7);

                    try {
                        Claims claims = jwtUtils.validateTokenAndGetClaims(token);

                        String userEmail = claims.getSubject();
                        String userRole=claims.get("role",String.class);
                        Integer userId=claims.get("userId",Integer.class);
                        ServerWebExchange modifiedExchange = exchange.mutate()
                                .request(exchange.getRequest().mutate()
                                        .header("X-User-Id", String.valueOf(userId))
                                        .header("X-User-Role", userRole)
                                        .header("X-User-Email", userEmail)
                                        .build())
                                .build();
                     authorizationFilter.checkAuthorization(modifiedExchange);
                     return chain.filter(modifiedExchange);

                    } catch (Exception e) {
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        throw new RuntimeException("Invalid Token");
                    }
                }
            }

            return chain.filter(exchange);
        };
    }

    public static class Config {
        // empty for now
    }
}