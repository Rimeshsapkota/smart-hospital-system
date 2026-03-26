package com.smarthospital.authservice.hospitaladmin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smarthospital.authservice.systemadmin.HospitalDetail;
import com.smarthospital.common_lib.entity.BaseEntity;
import com.smarthospital.common_lib.entity.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Builder;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;
    private String specialization;    // Cardiology, Neurology etc
    private String qualification;     // MBBS, MD etc
    private String licenseNumber;     // medical license
    private Integer experienceYears;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private DoctorStatus status;
    private boolean active;
}