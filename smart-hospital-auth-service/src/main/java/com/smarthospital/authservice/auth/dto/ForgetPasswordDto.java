package com.smarthospital.authservice.auth.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ForgetPasswordDto {
    private String code;
    private String newPassword;
    private String confirmPassword;

    public ForgetPasswordDto(String code){
        this.code=code;
    }
}
