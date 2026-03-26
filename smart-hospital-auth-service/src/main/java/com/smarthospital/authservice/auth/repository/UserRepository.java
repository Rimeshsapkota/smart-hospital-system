package com.smarthospital.authservice.auth.repository;



import com.smarthospital.authservice.auth.entity.User;
import com.smarthospital.common_lib.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);


    boolean existsByRole(Role role);
}

