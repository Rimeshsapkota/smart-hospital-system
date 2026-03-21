package com.smarthospital.authservice.admin;

import com.smarthospital.authservice.shared.ApiURL;
import com.smarthospital.authservice.shared.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HospitalRegisterController {
    private final HospitalRegisterService hospitalService;

    /**
     * fromCallable = "don't run now, just remember what to run — and let subscribeOn decide who runs it."
     * @param dto to display the user response
     * @return user response
     */
    @PostMapping(ApiURL.HOSPITAL_REGISTER)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Mono<ResponseEntity<UserResponse>> registerHospital(@RequestBody HospitalDetaiRequestDto dto) {
        return Mono.fromCallable(() -> hospitalService.hospitalRegisterInSystem(dto))
                .subscribeOn(Schedulers.boundedElastic())
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response));
    }

    @GetMapping(ApiURL.UPDATE_HOSPITAL_DETAIL)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Mono<ResponseEntity<UserResponse>> updateHospitalDetailBySystemAdmin(@RequestParam Long id, @RequestBody HospitalDetaiRequestDto hospitalDetaiRequestDto) {
        return Mono.fromCallable(()->hospitalService.updateHospitalDetailBySystemAdmin(id, hospitalDetaiRequestDto))
                .subscribeOn(Schedulers.boundedElastic())
                .map(response->ResponseEntity.status(HttpStatus.CREATED).body(response));
    }

    @GetMapping(ApiURL.ACTIVE_HOSPITAL_IN_SYSTEM)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Mono<ResponseEntity<List<HospitalDetailResponseDto>>> getAllActiveHospitals() {
        return Mono.fromCallable(hospitalService::getAllActiveHospitals)
                .subscribeOn(Schedulers.boundedElastic())
                .map(ResponseEntity::ok);
    }

}
