package com.smarthospital.authservice.systemadmin;

import com.smarthospital.common_lib.entity.Role;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class HospitalDetailResponseDto {
    private Long id;
    private String hospitalName;
    private String hospitalAddress;
    private String contactNumber;
}
