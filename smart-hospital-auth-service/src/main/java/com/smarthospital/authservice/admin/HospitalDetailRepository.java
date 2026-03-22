package com.smarthospital.authservice.admin;

import com.smarthospital.authservice.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HospitalDetailRepository extends JpaRepository<HospitalDetail,Long> {
    Optional<HospitalDetail> findByEmail(String email);
    boolean existsByEmail(String email);
    List<HospitalDetail> findByActiveTrue();

}
