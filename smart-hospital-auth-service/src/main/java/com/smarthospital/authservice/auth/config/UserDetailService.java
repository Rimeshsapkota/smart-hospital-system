package com.smarthospital.authservice.auth.config;


import com.smarthospital.authservice.auth.entity.User;
import com.smarthospital.authservice.auth.repository.UserRepository;
import com.smarthospital.common_lib.exception.UserNotFoundException;
import com.smarthospital.common_lib.shared.MessageConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailService {

    private final UserRepository userRepository;

    public Mono<CustomUserDetails> findByUsername(String email) {
        return Mono.fromCallable(() -> {
            Optional<User> user = userRepository.findByEmail(email);

            if (user.isEmpty()) {
                throw new UserNotFoundException(MessageConstant.USER_NOT_FOUND);
            }
            return new CustomUserDetails(
                    user.get().getUserId(),
                    user.get().getEmail(),
                    user.get().getPassword(),
                    user.get().getRole()
            );
        }).subscribeOn(Schedulers.boundedElastic()); // 🔥 important
    }
}