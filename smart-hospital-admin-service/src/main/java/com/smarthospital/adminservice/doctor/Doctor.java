package com.smarthospital.adminservice.doctor;

import com.smarthospital.common_lib.entity.BaseEntity;
import com.smarthospital.common_lib.entity.Role;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;
    private String specialization;    // Cardiology, Neurology etc
    private String qualification;     // MBBS, MD etc
    private String licenseNumber;     // medical license
    private Integer experienceYears;
    @Enumerated(EnumType.STRING)
    private DoctorStatus status;
   private Integer userId;
}