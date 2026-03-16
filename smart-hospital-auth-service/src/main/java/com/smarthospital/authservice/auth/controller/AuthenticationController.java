package com.smarthospital.authservice.auth.controller;

import com.smarthospital.authservice.auth.config.JwtAuthenticationResponse;
import com.smarthospital.authservice.auth.dto.SignUpRequest;
import com.smarthospital.authservice.auth.dto.SigninRequest;
import com.smarthospital.authservice.auth.service.AuthServiceImpl;
import com.smarthospital.authservice.shared.ApiURL;
import com.smarthospital.authservice.shared.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthServiceImpl authServiceImpl;

    @PostMapping(ApiURL.USER_SIGN_UP)
    public UserResponse userSignup(@RequestBody @Validated SignUpRequest request) {
        return authServiceImpl.signup(request);
    }


    @PostMapping(ApiURL.USER_SIGN_IN)
    public Mono<ResponseEntity<JwtAuthenticationResponse>> signin(@RequestBody SigninRequest request) {
        return authServiceImpl.signin(request)
                .map(ResponseEntity::ok);
    }


}
