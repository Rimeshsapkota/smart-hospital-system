package com.smarthospital.authservice.admin;

import com.smarthospital.authservice.exception.AlreadyExistException;
import com.smarthospital.authservice.exception.NotFoundException;
import com.smarthospital.authservice.exception.UserNotFoundException;
import com.smarthospital.authservice.shared.MessageConstant;
import com.smarthospital.authservice.shared.UserResponse;
import com.smarthospital.common_lib.entity.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HospitalRegisterService {
    private final HospitalDetailRepository hospitalDetailRepository;
    private final PasswordEncoder passwordEncoder;
    public HospitalRegisterService(HospitalDetailRepository hospitalDetailRepository, PasswordEncoder passwordEncoder){
        this.hospitalDetailRepository=hospitalDetailRepository;
        this.passwordEncoder=passwordEncoder;
    }
    public UserResponse hospitalRegisterInSystem(HospitalDetaiRequestDto dto) {
        if (hospitalDetailRepository.existsByEmail(dto.getEmail())) {
            throw new AlreadyExistException(MessageConstant.ALREADY_REGISTER);
        }
        HospitalDetail hospital = HospitalDetail.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .role(Role.HOSPITAL_ADMIN)
                .hospitalName(dto.getHospitalName())
                .hospitalAddress(dto.getHospitalAddress())
                .contactNumber(dto.getContactNumber())
                .active(true)
                .build();
        hospitalDetailRepository.save(hospital);
        return UserResponse.builder()
                .message("Hospital registered successfully")
                .build();
    }
    public UserResponse updateHospitalDetailBySystemAdmin(Long id, HospitalDetaiRequestDto dto) {
        HospitalDetail existingHospital = hospitalDetailRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Hospital not found"));
        existingHospital.setUsername(dto.getUsername());
        existingHospital.setEmail(dto.getEmail());
        existingHospital.setHospitalName(dto.getHospitalName());
        existingHospital.setHospitalAddress(dto.getHospitalAddress());
        existingHospital.setContactNumber(dto.getContactNumber());
        hospitalDetailRepository.save(existingHospital);
        return UserResponse.builder()
                .message("Hospital updated successfully")
                .build();
    }

        public List<HospitalDetailResponseDto> getAllActiveHospitals() {
            return hospitalDetailRepository.findByActiveTrue()
                    .stream()
                    .map(hospital -> HospitalDetailResponseDto.builder()
                            .id(hospital.getId())
                            .username(hospital.getUsername())
                            .email(hospital.getEmail())
                            .hospitalName(hospital.getHospitalName())
                            .hospitalAddress(hospital.getHospitalAddress())
                            .contactNumber(hospital.getContactNumber())
                            .role(hospital.getRole())
                            .build())
                    .collect(Collectors.toList());
        }
    }

