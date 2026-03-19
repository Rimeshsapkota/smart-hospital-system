package com.smarthospital.authservice.admin;

import com.smarthospital.authservice.shared.ApiURL;
import com.smarthospital.authservice.shared.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Controller
@RequiredArgsConstructor
public class HospitalRegisterController {
    private final HospitalRegisterService hospitalService;

    @PostMapping(ApiURL.HOSPITAL_REGISTER)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Mono<ResponseEntity<UserResponse>> registerHospital(@RequestBody HospitalDetailDto dto) {
        // Wrap blocking service logic in reactive Mono
        return Mono.fromCallable(() -> hospitalService.hospitalRegisterInSystem(dto))
                .subscribeOn(Schedulers.boundedElastic())
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response));
    }

}
