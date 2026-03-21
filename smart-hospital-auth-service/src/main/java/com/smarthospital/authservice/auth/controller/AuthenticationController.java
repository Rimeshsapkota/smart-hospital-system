package com.smarthospital.authservice.auth.controller;

import com.smarthospital.authservice.auth.config.JwtAuthenticationResponse;
import com.smarthospital.authservice.auth.dto.SignUpRequest;
import com.smarthospital.authservice.auth.dto.SigninRequest;
import com.smarthospital.authservice.auth.dto.UpdateUserDto;
import com.smarthospital.authservice.auth.service.AuthService;
import com.smarthospital.authservice.shared.ApiURL;
import com.smarthospital.authservice.shared.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthService authService;

    @PostMapping(ApiURL.USER_SIGN_UP)
    public UserResponse userSignup(@RequestBody @Validated SignUpRequest request) {
        return authService.signup(request);
    }


    @PostMapping(ApiURL.USER_SIGN_IN)
    public Mono<ResponseEntity<JwtAuthenticationResponse>> signin(@RequestBody SigninRequest request) {
        return authService.signin(request)
                .map(ResponseEntity::ok);
    }

  @GetMapping(ApiURL.USER_UPDATED)
    public UserResponse userResponse(@RequestParam Integer id, @RequestBody UpdateUserDto updateUserDto){
        return authService.updateUser(id, updateUserDto);
  }

}
