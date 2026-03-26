package com.smarthospital.authservice.bootstrap;

import com.smarthospital.authservice.auth.entity.User;
import com.smarthospital.common_lib.entity.Role;
import com.smarthospital.authservice.auth.repository.UserRepository;
import com.smarthospital.authservice.auth.service.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminBootstrap implements CommandLineRunner {

    private final UserRepository userRepository;
    private final AuthServiceImpl authServiceImpl;

    @Override
    public void run(String... args) throws Exception {
        // Check if an admin already exists
        boolean adminExists = userRepository.existsByRole(Role.ADMIN);
        if (!adminExists) {
            User admin = new User();
            admin.setEmail("admin1@gmail.com");
            admin.setRole(Role.ADMIN);
            admin.setPassword(this.passwordEncoder().encode("Admin1@#"));
            admin.setFirstName("Admin");
            admin.setLastName("Admin");

            userRepository.save(admin);
            System.out.println("Admin user created successfully!");
        } else {
            System.out.println("Admin user already exists, skipping bootstrap.");
        }
    }

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}