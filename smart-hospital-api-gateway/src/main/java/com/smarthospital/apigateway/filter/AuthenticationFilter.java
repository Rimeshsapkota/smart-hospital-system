package com.smarthospital.apigateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;

import java.util.Objects;

@Configuration
public class AuthenticationFilter extends AbstractGatewayFilterFactory<Authentication> {
    @Autowired
    private ApiGateWayJwtUtils jwtUtils;

    @Autowired
    private RouterValidator routerValidator;

    @Override
    public GatewayFilter apply(Authentication config) {
        return((exchange, chain) -> {
            if (routerValidator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }
                String authHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).getFirst();
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                    try {
                        jwtUtils.validateToken(authHeader);

                    } catch (Exception e) {
                        throw new RuntimeException("An unauthorized access to run application");
                    }
                }
            }
            return chain.filter(exchange);
        });

    }

}
