package com.smarthospital.authservice.admin;

import com.smarthospital.authservice.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HospitalDetailRepository extends JpaRepository<HospitalDetail,Long> {
    Optional<HospitalDetail> findByEmail(String email);
    boolean existsByEmail(String email);

    boolean findByActiveTrue();
}
