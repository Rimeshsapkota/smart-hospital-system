package com.smarthospital.authservice.exception;

import lombok.NoArgsConstructor;

import javax.naming.AuthenticationException;

@NoArgsConstructor
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
