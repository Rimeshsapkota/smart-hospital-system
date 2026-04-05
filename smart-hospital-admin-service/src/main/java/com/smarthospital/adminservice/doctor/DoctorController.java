package com.smarthospital.adminservice.doctor;

import com.smarthospital.common_lib.shared.ApiURL;
import com.smarthospital.common_lib.shared.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    @PostMapping(ApiURL.DOCTOR_REGISTER)
    public UserResponse registerDoctor(@RequestBody DoctorRequestDto doctorRequestDto,@RequestHeader ("X-User-Id") Integer userId){
        return doctorService.doctorRegister(doctorRequestDto,userId);
    }
}
