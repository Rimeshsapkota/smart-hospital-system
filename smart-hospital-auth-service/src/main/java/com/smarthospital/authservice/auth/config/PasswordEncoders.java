//package com.smarthospital.authservice.auth.config;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class PasswordEncoders {
//    @Value("${password.salt}")
//    private String salt;
//
//     private final PasswordEncoder passwordEncoder;
//
//    public String encodePassword(String password) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String saltedPassword = password + salt;
//        return passwordEncoder.encode(saltedPassword);
//    }
//
//    public boolean matches(String enteredPassword, String storedHashedPassword) {
//        return passwordEncoder.matches(enteredPassword, storedHashedPassword);
//    }
//
//}