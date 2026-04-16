package com.smarthospital.authservice.auth.mapper;

import com.smarthospital.authservice.auth.dto.SignUpRequest;
import com.smarthospital.authservice.auth.entity.User;
import com.smarthospital.common_lib.pagination.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements GenericMapper<User, SignUpRequest> {

    @Override
    public SignUpRequest toDto(User user) {
        if (user == null) return null;
        SignUpRequest dto = new SignUpRequest();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }

    @Override
    public User toEntity(SignUpRequest dto) {
        return null; // not needed now
    }
}
