package com.smarthospital.adminservice.doctor;

import com.smarthospital.common_lib.shared.MessageConstant;
import com.smarthospital.common_lib.shared.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    public UserResponse doctorRegister(DoctorRequestDto doctorRequestDto,Integer userId){
        Optional<Doctor> userDetailFromDB = doctorRepository.findByUserId(userId);
        if (userDetailFromDB.isPresent()){
            Doctor doctor = userDetailFromDB.get();
            this.updateDoctorRegister(doctorRequestDto,doctor);
            return new UserResponse(MessageConstant.SUCCESSFULLY_UPDATED,null);
        }
        Doctor doctor = Doctor.builder()
                .userId(userId)
                .licenseNumber(doctorRequestDto.getLicenseNumber())
                .status(DoctorStatus.ACTIVE)
                .specialization(doctorRequestDto.getSpecialization())
                .experienceYears(doctorRequestDto.getExperienceYears())
                .qualification(doctorRequestDto.getQualification())
                .build();
         doctorRepository.save(doctor);
         return new UserResponse(MessageConstant.SUCCESSFULLY_SAVE,doctor);
    }

    private void updateDoctorRegister(DoctorRequestDto doctorRequestDto,Doctor doctor){
          doctor.setStatus(DoctorStatus.ACTIVE);
          doctor.setQualification(doctorRequestDto.getQualification());
          doctor.setSpecialization(doctorRequestDto.getSpecialization());
          doctor.setLicenseNumber(doctorRequestDto.getLicenseNumber());
          doctor.setExperienceYears(doctorRequestDto.getExperienceYears());
          doctorRepository.save(doctor);
    }

}
