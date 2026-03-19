package com.smarthospital.authservice.admin;

import com.smarthospital.authservice.exception.AlreadyExistException;
import com.smarthospital.authservice.shared.MessageConstant;
import com.smarthospital.authservice.shared.UserResponse;
import com.smarthospital.common_lib.entity.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HospitalRegisterService {
    private final HospitalDetailRepository hospitalDetailRepository;
    private final PasswordEncoder passwordEncoder;
    public HospitalRegisterService(HospitalDetailRepository hospitalDetailRepository, PasswordEncoder passwordEncoder){
        this.hospitalDetailRepository=hospitalDetailRepository;
        this.passwordEncoder=passwordEncoder;
    }
    public UserResponse hospitalRegisterInSystem(HospitalDetailDto dto) {
        Optional<HospitalDetail> hospitalDetail = hospitalDetailRepository.findByEmail(dto.getEmail());
        if (hospitalDetail.isPresent()) {
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
                .obj(null)
                .message("Hospital registered successfully")
                .build();
    }
}
