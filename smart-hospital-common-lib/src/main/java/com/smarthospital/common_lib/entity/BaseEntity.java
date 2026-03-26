package com.smarthospital.common_lib.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor   // ✅ required
@AllArgsConstructor
public abstract class BaseEntity {
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String password;
    private String address;
    private double contactNumber;
    private int age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private ParentRelationship parentRelationship;
    private double parentContactNumber;
    @Temporal(TemporalType.TIMESTAMP)
    private Date forgetPasswordCodeTimestamp;
    @Column(updatable = false)
    private LocalDateTime createdAt;
    private String bloodGroup;
    private LocalDateTime updatedAt;
    private String parentName;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
