package com.smarthospital.apigateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator {
    public static final List<String> openApiEndPoint=List.of(
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
            "/patient-service/v3/api-docs"
    );

    public Predicate<ServerHttpRequest> isSecured=
            serverHttpRequest -> openApiEndPoint
                    .stream()
                    .noneMatch(uri->serverHttpRequest.getURI().getPath().contains(uri));

}
