package com.smarthospital.authservice.auth.service;


import com.smarthospital.authservice.auth.config.JwtAuthenticationResponse;
import com.smarthospital.authservice.auth.dto.SignUpRequest;
import com.smarthospital.authservice.auth.dto.SigninRequest;
import com.smarthospital.authservice.auth.dto.UpdateUserDto;
import com.smarthospital.common_lib.pagination.PageResult;
import com.smarthospital.common_lib.pagination.PaginationRequest;
import com.smarthospital.common_lib.shared.UserResponse;
import org.springframework.security.core.Authentication;
import reactor.core.publisher.Mono;

public interface AuthService {

    UserResponse signup(SignUpRequest request, Authentication authentication);

    Mono<JwtAuthenticationResponse> signin(SigninRequest request);

    UserResponse  updateUser(Integer id, UpdateUserDto updateUserDto);

     PageResult<SignUpRequest> getAllUsers(PaginationRequest request);



    }