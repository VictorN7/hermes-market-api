package com.hermes.market.application.service;

import com.hermes.market.application.dto.request.LoginRequest;
import com.hermes.market.application.dto.response.LoginResponse;
import com.hermes.market.application.exception.BusinessException;
import com.hermes.market.application.mapper.UserMapper;
import com.hermes.market.domain.user.User;
import com.hermes.market.domain.user.UserStatus;
import com.hermes.market.infrastructure.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public LoginResponse authenticate(LoginRequest loginRequest) {
        User user = userRepository.findByEmailIgnoreCase(loginRequest.getEmail().trim().replaceAll("\\s+", " ")).orElseThrow(() -> new BusinessException("Invalid email or password"));
        if (!user.getStatus().equals(UserStatus.ACTIVE)) {
            throw new BusinessException("User is inactive or blocked");
        }

        if (!passwordEncoder.matches(loginRequest.getPassword().trim().replaceAll("\\s+", " "), user.getPassword())) {
            throw new BusinessException("Invalid email or password");
        }

        return UserMapper.toLogin("token here", user);
    }


}
