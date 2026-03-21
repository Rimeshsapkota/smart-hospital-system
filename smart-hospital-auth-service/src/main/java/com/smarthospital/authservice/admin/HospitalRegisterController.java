package com.smarthospital.authservice.admin;

import com.smarthospital.authservice.shared.ApiURL;
import com.smarthospital.authservice.shared.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HospitalRegisterController {
    private final HospitalRegisterService hospitalService;

    /**
     * fromCallable = "don't run now, just remember what to run — and let subscribeOn decide who runs it."
     * @param dto to display the user response
     * @return userresponse
     */
    @PostMapping(ApiURL.HOSPITAL_REGISTER)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Mono<ResponseEntity<UserResponse>> registerHospital(@RequestBody HospitalDetaiRequestDto dto) {
        return Mono.fromCallable(() -> hospitalService.hospitalRegisterInSystem(dto))
                .subscribeOn(Schedulers.boundedElastic())
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response));
    }

    @GetMapping("/active/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Mono<ResponseEntity<List<HospitalDetailResponseDto>>> getAllActiveHospitals() {
        return Mono.fromCallable(hospitalService::getAllActiveHospitals)
                .subscribeOn(Schedulers.boundedElastic())
                .map(ResponseEntity::ok);
    }

}
