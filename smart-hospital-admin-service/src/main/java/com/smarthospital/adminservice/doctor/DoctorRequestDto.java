package com.smarthospital.adminservice.doctor;

import com.smarthospital.common_lib.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class DoctorRequestDto {
    private Integer userId;
    private Long doctorId;
    private String specialization;    // Cardiology, Neurology etc
    private String qualification;     // MBBS, MD etc
    private String licenseNumber;     // medical license
    private Integer experienceYears;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private DoctorStatus status;
}
