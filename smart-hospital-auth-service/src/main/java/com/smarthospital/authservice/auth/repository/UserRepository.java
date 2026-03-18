package com.smarthospital.authservice.auth.repository;



import com.smarthospital.authservice.auth.entity.Role;
import com.smarthospital.authservice.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);


    boolean existsByRole(Role role);
}

