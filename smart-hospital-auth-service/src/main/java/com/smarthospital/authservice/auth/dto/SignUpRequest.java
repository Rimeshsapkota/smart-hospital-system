package com.smarthospital.authservice.auth.dto;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.smarthospital.common_lib.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    @Pattern(regexp = "[a-zA-Z]{2,50}", message = "firstName must be between 2 and 50 characters and contain only letters")
    @JsonAlias("first_name")
    private String firstName;

    public String middleName;

    @Pattern(regexp = "[a-zA-Z]{2,50}", message = "lastName must be between 2 and 50 characters and contain only letters")
    private String lastName;

    @Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Please provide a valid email address")
    private String email;

    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,20}$", message = "Password must meet the specified criteria")
    private String password;

    private String role;

}