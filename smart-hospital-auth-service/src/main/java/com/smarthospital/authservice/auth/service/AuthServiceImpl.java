package com.smarthospital.authservice.auth.service;



import com.smarthospital.authservice.auth.config.JwtAuthenticationResponse;
import com.smarthospital.authservice.auth.config.JwtService;
import com.smarthospital.authservice.auth.config.UserDetailService;
import com.smarthospital.authservice.auth.dto.SignUpRequest;
import com.smarthospital.authservice.auth.dto.SigninRequest;
import com.smarthospital.authservice.auth.dto.UpdateUserDto;
import com.smarthospital.authservice.auth.entity.User;
import com.smarthospital.common_lib.entity.Role;
import com.smarthospital.authservice.auth.repository.UserRepository;
import com.smarthospital.common_lib.exception.InvalidUserCredentialException;
import com.smarthospital.common_lib.exception.AlreadyExistException;
import com.smarthospital.common_lib.shared.MessageConstant;
import com.smarthospital.common_lib.shared.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Optional;

/**
 * This class is used to create the login and signup
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoders;
    private final JwtService jwtService;
    private final UserDetailService userService;


    @Override
    public UserResponse signup(SignUpRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        if (userOptional.isPresent()) {
            throw new AlreadyExistException(MessageConstant.ALREADY_REGISTER);
        }
        User patient1 = new User();
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoders.encode(request.getPassword()))
                .role(Role.PATIENT)
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
}