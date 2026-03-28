package com.smarthospital.authservice.auth.service;


import com.smarthospital.authservice.auth.config.JwtAuthenticationResponse;
import com.smarthospital.authservice.auth.dto.SignUpRequest;
import com.smarthospital.authservice.auth.dto.SigninRequest;
import com.smarthospital.authservice.auth.dto.UpdateUserDto;
import com.smarthospital.common_lib.shared.UserResponse;
import reactor.core.publisher.Mono;

public interface AuthService {

    UserResponse signup(SignUpRequest request);

    Mono<JwtAuthenticationResponse> signin(SigninRequest request);

    UserResponse  updateUser(Integer id, UpdateUserDto updateUserDto);



    }