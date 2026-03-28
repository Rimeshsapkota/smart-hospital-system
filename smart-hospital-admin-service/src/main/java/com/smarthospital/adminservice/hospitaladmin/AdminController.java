package com.smarthospital.adminservice.hospitaladmin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('USER')")
    public String home(){
       return "home";
    }
}
