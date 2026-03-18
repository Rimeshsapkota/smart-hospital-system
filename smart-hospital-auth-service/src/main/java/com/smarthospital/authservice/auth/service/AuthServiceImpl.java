package com.smarthospital.authservice.auth.service;



import com.smarthospital.authservice.auth.config.JwtAuthenticationResponse;
import com.smarthospital.authservice.auth.config.JwtService;
import com.smarthospital.authservice.auth.config.UserDetailService;
import com.smarthospital.authservice.auth.dto.SignUpRequest;
import com.smarthospital.authservice.auth.dto.SigninRequest;
import com.smarthospital.authservice.auth.dto.UpdateUserDto;
import com.smarthospital.authservice.auth.entity.Role;
import com.smarthospital.authservice.auth.entity.User;
import com.smarthospital.authservice.auth.repository.UserRepository;
import com.smarthospital.authservice.exception.InvalidUserCredentialException;
import com.smarthospital.authservice.exception.UserAlreadyExistException;
import com.smarthospital.authservice.shared.MessageConstant;
import com.smarthospital.authservice.shared.UserResponse;
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
            throw new UserAlreadyExistException(MessageConstant.ALREADY_REGISTER);
        }
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
        User user = userFromDB.get();
        user.setAge(updateUserDto.getAge());
        user.setContactNumber(updateUserDto.getContactNumber());
        user.setAddress(updateUserDto.getAddress());
        user.setGender(updateUserDto.getGender());
        user.setBloodGroup(updateUserDto.getBloodGroup());
        user.setParentContactNumber(updateUserDto.getParentContactNumber());
        userRepository.save(user);
        return new UserResponse(MessageConstant.SUCCESSFULLY_UPDATED,null);
    }
}