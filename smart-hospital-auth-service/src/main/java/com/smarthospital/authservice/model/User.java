package com.smarthospital.authservice.model;

import com.smarthospital.common_lib.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean enabled = true;
}
