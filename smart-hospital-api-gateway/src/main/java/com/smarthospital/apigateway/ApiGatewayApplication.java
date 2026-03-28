package com.smarthospital.apigateway;

import com.smarthospital.apigateway.filter.AuthenticationFilter;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(info = @Info(title = "API Gateway", version = "1.0", description = "Documentation API Gateway v1.0"))
public class ApiGatewayApplication {
	@Autowired
	private AuthenticationFilter authenticationFilter;

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
//	@Bean
//	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
//		return builder
//				.routes()
//				.route(r -> r.path("/auth-service/**")
//						.filters(f ->
//								f.rewritePath("/auth-service/(?<path>.*)", "/$\\{path}"))
//						.uri("lb://auth-service"))
//				.route(r -> r.path("/patient-service/**")
//						.filters(f -> f.rewritePath("/patient-service/(?<path>.*)", "/$\\{path}"))
//						.uri("lb://patient-service"))
//				.route(r -> r.path("/admin-service/**")
//						.filters(f -> f.rewritePath("/admin-service/(?<path>.*)", "/$\\{path}"))
//						.uri("lb://admin-service"))
//				.build();
//	}
}
