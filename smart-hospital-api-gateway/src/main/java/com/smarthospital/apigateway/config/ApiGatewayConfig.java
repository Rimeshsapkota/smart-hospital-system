package com.smarthospital.apigateway.config;

import com.smarthospital.apigateway.filter.TokenGatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {
    private final TokenGatewayFilter tokenGatewayFilter;
    public ApiGatewayConfig(TokenGatewayFilter tokenGatewayFilter){
        this.tokenGatewayFilter=tokenGatewayFilter;
    }
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-service", r -> r
                        .path("/auth-service/**")
                        .filters(f -> f
                                .filter(tokenGatewayFilter)
                                .rewritePath("/auth-service/(?<segment>.*)", "/${segment}")
                        )
                        .uri("lb://auth-service"))
                .route("patient-service", r -> r
                        .path("/patient-service/**")
                        .filters(f -> f
                                .filter(tokenGatewayFilter)
                                .rewritePath("/patient-service/(?<segment>.*)", "/${segment}")
                        )
                        .uri("lb://patient-service"))
                .build();
    }
}
