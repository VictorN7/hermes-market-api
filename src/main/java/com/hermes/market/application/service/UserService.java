package com.hermes.market.application.service;

import com.hermes.market.application.dto.request.UserPasswordRequest;
import com.hermes.market.application.dto.request.UserRequest;
import com.hermes.market.application.dto.request.UserUpdateRequest;
import com.hermes.market.application.dto.response.UserResponse;
import com.hermes.market.application.exception.BusinessException;
import com.hermes.market.application.exception.ResourceNotFoundException;
import com.hermes.market.application.mapper.UserMapper;
import com.hermes.market.domain.user.User;
import com.hermes.market.domain.user.UserStatus;
import com.hermes.market.infrastructure.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hermes.market.infrastructure.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, OrderRepository orderRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmailIgnoreCase(username).orElseThrow(() -> new UsernameNotFoundException("Credentials not found"));
    }

    @Transactional(readOnly = true)
    public Page<UserResponse> findAll(Pageable pageable) {
        Page<User> users = userRepository.findByStatus(UserStatus.ACTIVE.getCode(), pageable);
        return users.map(UserMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public UserResponse findById(Long id) {
        return UserMapper.toResponse(userRepository.findByIdAndStatus(id, UserStatus.ACTIVE.getCode()).orElseThrow(() -> new ResourceNotFoundException("User not found")));
    }

    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
        if (userRepository.existsByCpf(userRequest.getCpf().trim().replaceAll("\\s+", " ")) ||
                userRepository.existsByEmail(userRequest.getEmail().trim().replaceAll("\\s+", " "))) {
            throw new BusinessException("User is already exists");
        }

        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());

        User user = new User(userRequest.getName().trim().replaceAll("\\s+", " "),
                userRequest.getEmail().trim().replaceAll("\\s+", " "),
                encodedPassword,
                userRequest.getBirthDate(),
                userRequest.getCpf().trim().replaceAll("\\s+", " "));

        return UserMapper.toResponse(userRepository.save(user));
    }

    @Transactional
    public UserResponse updateUser(Long userId, UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if(!user.getEmail().equalsIgnoreCase(userUpdateRequest.getEmail().trim().replaceAll("\\s+", " ")) &&
                userRepository.existsByEmail(userUpdateRequest.getEmail().trim().replaceAll("\\s+", " "))) {
            throw new BusinessException("Email is already exists");
        }
        user.updateUser(userUpdateRequest.getName().trim().replaceAll("\\s+", " "),
                userUpdateRequest.getEmail().trim().replaceAll("\\s+", " "),
                userUpdateRequest.getBirthDate());
        userRepository.save(user);
        return UserMapper.toResponse(user);
    }

    @Transactional
    public void updatePassword(Long userId, UserPasswordRequest userPasswordRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.updatePassword(userPasswordRequest.getNewPassword().trim().replaceAll("\\s+", " "),
                userPasswordRequest.getConfirmPassword().trim().replaceAll("\\s+", " "),
                userPasswordRequest.getCurrentPassword().trim().replaceAll("\\s+", " "));
        userRepository.save(user);
    }

    @Transactional
    public void deactivateUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.deactivate();
        userRepository.save(user);
    }

    @Transactional
    public void activateUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.activate();
        userRepository.save(user);
    }

    @Transactional
    public void blockUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.block();
        userRepository.save(user);
    }

    @Transactional
    public void unlockUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.unlock();
        userRepository.save(user);
    }

    @Transactional
    public void deleteOrDeactivateUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (!orderRepository.existsByUserId(userId)) {
            userRepository.delete(user);
        } else {
            user.deactivate();
            userRepository.save(user);
        }
    }

    @Transactional(readOnly = true)
    public Page<UserResponse> findInactiveUsers(Pageable pageable) {
        Page<User> users = userRepository.findByStatus(UserStatus.INACTIVE.getCode(), pageable);
        return users.map(UserMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public Page<UserResponse> findBlockedUsers(Pageable pageable) {
        Page<User> users = userRepository.findByStatus(UserStatus.BLOCKED.getCode(), pageable);
        return users.map(UserMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public UserResponse findInactiveUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (!user.getStatus().equals(UserStatus.INACTIVE)) {
            throw new ResourceNotFoundException("Inactive user not found");
        }
        return UserMapper.toResponse(user);
    }

}