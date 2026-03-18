package com.smarthospital.authservice.shared;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Builder
public class UserResponse {
    private String message;
    private Object obj;
}
