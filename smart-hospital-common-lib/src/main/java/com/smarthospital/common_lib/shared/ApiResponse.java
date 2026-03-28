package com.smarthospital.common_lib.shared;


import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Builder
@Component
public class ApiResponse implements Serializable {
    protected String message;
    private String operation;
    
}