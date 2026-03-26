package com.smarthospital.authservice.systemadmin;

import com.smarthospital.common_lib.entity.Role;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class HospitalDetaiRequestDto implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Role role;
    private String hospitalName;
    private String hospitalAddress;
    private String contactNumber;
    private boolean active;
}
