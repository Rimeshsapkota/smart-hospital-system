package com.smarthospital.patientservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {

    @GetMapping("/home")
    public String checkEndPoint(){
        return"succesfull work";
    }
}
