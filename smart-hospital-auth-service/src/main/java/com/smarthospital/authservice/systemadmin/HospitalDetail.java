package com.smarthospital.authservice.systemadmin;

import com.smarthospital.authservice.auth.entity.User;
import com.smarthospital.common_lib.entity.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hospital_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HospitalDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String hospitalName;
    private String hospitalAddress;
    private String contactNumber;
    private boolean active = false;
    @OneToOne
    private User user;
}
