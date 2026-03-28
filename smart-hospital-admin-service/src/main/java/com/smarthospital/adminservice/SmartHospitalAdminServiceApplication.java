package com.smarthospital.adminservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SmartHospitalAdminServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartHospitalAdminServiceApplication.class, args);
	}

}
