package com.smarthospital.authservice.auth.dto;

import com.smarthospital.common_lib.entity.Gender;
import com.smarthospital.common_lib.entity.ParentRelationship;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@Builder
public class UpdateUserDto {
    private String address;
    private double contactNumber;
    private int age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private ParentRelationship parentRelationship;
    private double parentContactNumber;
    private String bloodGroup;
    private String parentName;
}
