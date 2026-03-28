package com.smarthospital.common_lib.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidUserCredentialException extends RuntimeException{
    public InvalidUserCredentialException(String message) {
        super(message);
    }
}
