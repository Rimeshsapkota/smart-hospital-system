package com.smarthospital.authservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private String message;
    private String token;
    private String username;

    public AuthResponse(String message, String token, String username) {
        this.message = message;
        this.token = token;
        this.username = username;
    }


}
