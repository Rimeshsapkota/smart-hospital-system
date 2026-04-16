package com.smarthospital.authservice.auth.service;



import com.smarthospital.authservice.auth.config.JwtAuthenticationResponse;
import com.smarthospital.authservice.auth.config.JwtService;
import com.smarthospital.authservice.auth.config.UserDetailService;
import com.smarthospital.authservice.auth.dto.SignUpRequest;
import com.smarthospital.authservice.auth.dto.SigninRequest;
import com.smarthospital.authservice.auth.dto.UpdateUserDto;
import com.smarthospital.authservice.auth.entity.User;
import com.smarthospital.authservice.systemadmin.HospitalDetaiRequestDto;
import com.smarthospital.authservice.systemadmin.HospitalDetail;
import com.smarthospital.authservice.systemadmin.HospitalDetailRepository;
import com.smarthospital.authservice.systemadmin.HospitalRegisterService;
import com.smarthospital.common_lib.entity.Role;
import com.smarthospital.authservice.auth.repository.UserRepository;
import com.smarthospital.common_lib.exception.InvalidUserCredentialException;
import com.smarthospital.common_lib.exception.AlreadyExistException;
import com.smarthospital.common_lib.exception.NotFoundException;
import com.smarthospital.common_lib.exception.UserNotFoundException;
import com.smarthospital.common_lib.pagination.BaseService;
import com.smarthospital.common_lib.pagination.GenericMapper;
import com.smarthospital.common_lib.pagination.PageResult;
import com.smarthospital.common_lib.pagination.PaginationRequest;
import com.smarthospital.common_lib.shared.MessageConstant;
import com.smarthospital.common_lib.shared.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * This class is used to create the login and signup
 */
@Service
@Slf4j
public class AuthServiceImpl extends BaseService<User, SignUpRequest,Integer> implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoders;
    private final JwtService jwtService;
    private final UserDetailService userService;
    private final HospitalRegisterService hospitalRegisterService;

    public AuthServiceImpl(UserRepository userRepository,
                           GenericMapper<User, SignUpRequest> mapper,
                           PasswordEncoder passwordEncoders,
                           JwtService jwtService,
                           UserDetailService userService,
                           HospitalRegisterService hospitalRegisterService) {
        super(userRepository, mapper);
        this.userRepository = userRepository;
        this.passwordEncoders = passwordEncoders;
        this.jwtService = jwtService;
        this.userService = userService;
        this.hospitalRegisterService = hospitalRegisterService;
    }

    @Override
    public UserResponse signup(SignUpRequest request,Authentication authentication) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        if (userOptional.isPresent()) {
            throw new AlreadyExistException(MessageConstant.ALREADY_REGISTER);
        }
        Role roleToAssign = extractRoleFromAuthentication(authentication,request);
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .middleName(request.getMiddleName())
                .email(request.getEmail())
                .password(passwordEncoders.encode(request.getPassword()))
                .role(roleToAssign)
                .build();
        userRepository.save(user);

        return new UserResponse(MessageConstant.SUCCESSFULLY_SAVE,null);
    }


    public Mono<JwtAuthenticationResponse> signin(SigninRequest request) {
        return userService.findByUsername(request.getEmail()) // reactive
                .flatMap(userDetails ->
                        Mono.fromCallable(() -> {
                            if (!passwordEncoders.matches(request.getPassword(), userDetails.getPassword())) {
                                throw new InvalidUserCredentialException(
                                        MessageConstant.INVALID_EMAIL_AND_PASSWORD_COMBINATION
                                );
                            }
                            String token = jwtService.generateToken(userDetails);
                            return new JwtAuthenticationResponse(token, MessageConstant.SUCCESSFULLY_LOGIN);
                        }).subscribeOn(Schedulers.boundedElastic())
                );
    }

    public UserResponse  updateUser(Integer id, UpdateUserDto updateUserDto){
        Optional<User> userFromDB = userRepository.findById(id);
        if (userFromDB.isEmpty()){
            return UserResponse.builder().message("user not exist").build();
        }
        User patient = userFromDB.get();
        patient.setAge(updateUserDto.getAge());
        patient.setContactNumber(updateUserDto.getContactNumber());
        patient.setAddress(updateUserDto.getAddress());
        patient.setGender(updateUserDto.getGender());
        patient.setBloodGroup(updateUserDto.getBloodGroup());
        patient.setParentContactNumber(updateUserDto.getParentContactNumber());
        userRepository.save(patient);
        return new UserResponse(MessageConstant.SUCCESSFULLY_UPDATED,null);
    }


    private Role extractRoleFromAuthentication(Authentication authentication, SignUpRequest signUpRequest) {
        if (authentication == null || authentication.getAuthorities().isEmpty()) {
            return Role.PATIENT;
        }
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            String roleString = authority.getAuthority();
            if (roleString.contains("HOSPITAL_ADMIN")) {
                if (signUpRequest.getRole().equals("DOCTOR")){
                    return Role.DOCTOR;
                }
                else if (signUpRequest.getRole().equals("STAFF")){
                    return Role.STAFF;
                }
                log.error("Role is not found {}", roleString);
                throw new NotFoundException("Role is not found");
            } else if (roleString.contains("SUPER_ADMIN")) {
                return Role.HOSPITAL_ADMIN;
            }
        }
        return null;
    }

    @Override
    public PageResult<SignUpRequest> getAllUsers(PaginationRequest request) {
        String sortField = Optional.ofNullable(request.getSortField())
                .orElse("userId");
        request.setSortField(sortField);
        return findAll(request);
    }

}