package com.smarthospital.apigateway.filter;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AuthorizationFilter {
    private static final Map<String, List<String>> PROTECTED_ENDPOINTS = new HashMap<>();

    public AuthorizationFilter() {
        // Format: "REQUEST_PATH" -> ["ALLOWED_ROLES"]

        // Admin only endpoints
        PROTECTED_ENDPOINTS.put("/patient-service/api/work", List.of("ADMIN"));
        PROTECTED_ENDPOINTS.put("/patient-service/api/admin/stats", List.of("ADMIN"));
        PROTECTED_ENDPOINTS.put("/patient-service/api/admin/users", List.of("ADMIN"));

        PROTECTED_ENDPOINTS.put("/patient-service/api/doctor/patients", List.of("ADMIN", "DOCTOR"));

        PROTECTED_ENDPOINTS.put("/patient-service/api/patient/profile", List.of("ADMIN", "DOCTOR", "PATIENT"));

    }
    public void checkAuthorization(ServerWebExchange exchange) {
        String path = exchange.getRequest().getURI().getPath();
        String role = exchange.getRequest().getHeaders().getFirst("X-USER-ROLE");
        for (Map.Entry<String,List<String>> entry : PROTECTED_ENDPOINTS.entrySet()){
            if (path.contains(entry.getKey())){
                List<String> allowedRequest = entry.getValue();
                if (!allowedRequest.contains(role)){
                    exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    throw new RuntimeException("User role '" + role + "' not allowed for this endpoint");
                }
            }
            break;
        }
    }
}
