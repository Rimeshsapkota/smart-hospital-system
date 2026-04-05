package com.smarthospital.authservice.systemadmin;

import com.smarthospital.authservice.auth.entity.User;
import com.smarthospital.authservice.auth.repository.UserRepository;
import com.smarthospital.common_lib.exception.AlreadyExistException;
import com.smarthospital.common_lib.exception.NotFoundException;
import com.smarthospital.common_lib.shared.MessageConstant;
import com.smarthospital.common_lib.shared.UserResponse;
import com.smarthospital.common_lib.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class HospitalRegisterService {
    private final HospitalDetailRepository hospitalDetailRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    public HospitalRegisterService(HospitalDetailRepository hospitalDetailRepository, PasswordEncoder passwordEncoder, UserRepository userRepository){
        this.hospitalDetailRepository=hospitalDetailRepository;
        this.passwordEncoder=passwordEncoder;
        this.userRepository = userRepository;
    }
    public UserResponse hospitalRegisterInSystem(HospitalDetaiRequestDto dto,Authentication authentication) {
        String email = authentication.getName();
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            HospitalDetail hospital = HospitalDetail.builder()
                    .hospitalName(dto.getHospitalName())
                    .hospitalAddress(dto.getHospitalAddress())
                    .contactNumber(dto.getContactNumber())
                    .user(userOptional.get())
                    .active(true)
                    .build();
            hospitalDetailRepository.save(hospital);
            return UserResponse.builder()
                    .message("Hospital registered successfully")
                    .build();
        }
        else {
            log.error("hospital admin is not register!! please contact to system admin");
            throw new NotFoundException("hospital is not registered in the system through system admin");
        }
    }
    public UserResponse updateHospitalDetailByHospitalAdmin(Long id, HospitalDetaiRequestDto dto) {
        HospitalDetail existingHospital = hospitalDetailRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Hospital not found"));
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
                            .hospitalName(hospital.getHospitalName())
                            .hospitalAddress(hospital.getHospitalAddress())
                            .contactNumber(hospital.getContactNumber())
                            .build())
                    .collect(Collectors.toList());
        }
    }

