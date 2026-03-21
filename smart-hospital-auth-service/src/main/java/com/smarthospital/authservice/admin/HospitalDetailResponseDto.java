package com.smarthospital.authservice.admin;

import com.smarthospital.common_lib.entity.Role;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class HospitalDetailResponseDto {
    private Long id;
    private String username;
    private String email;
    private Role role;
    private String hospitalName;
    private String hospitalAddress;
    private String contactNumber;
}
