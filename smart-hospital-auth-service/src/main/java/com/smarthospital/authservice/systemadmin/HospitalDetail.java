package com.smarthospital.authservice.systemadmin;

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
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(nullable = false)
    private String hospitalName;
    private String hospitalAddress;
    private String contactNumber;
    private boolean active = false;
}
