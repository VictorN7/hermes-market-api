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
import org.springframework.stereotype.Service;

import com.hermes.market.infrastructure.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public UserService(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    public Page<UserResponse> findAll(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(UserMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public UserResponse findById(Long id) {
        return UserMapper.toResponse(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found")));
    }

    @Transactional
    public UserResponse createUser(UserRequest userRequest) {

        if (userRepository.existsByCpf(userRequest.getCpf()) ||  userRepository.existsByEmail(userRequest.getEmail())) {
            throw new BusinessException("User is already exists");
        }

        return UserMapper.toResponse(userRepository.save(UserMapper.toCreate(userRequest)));
    }

    @Transactional
    public UserResponse updateUser(Long userId, UserUpdateRequest userUpdateRequest) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if(!user.getEmail().equalsIgnoreCase(userUpdateRequest.getEmail()) && userRepository.existsByEmail(userUpdateRequest.getEmail())) {
            throw new BusinessException("Email is already exists");
        }

        user.updateUser(userUpdateRequest.getName(), userUpdateRequest.getEmail(), userUpdateRequest.getBirthDate());
        userRepository.save(user);

        return UserMapper.toResponse(user);
    }

    @Transactional
    public void updatePassword(Long userId, UserPasswordRequest userPasswordRequest) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.updatePassword(userPasswordRequest.getNewPassword(), userPasswordRequest.getConfirmPassword(), userPasswordRequest.getCurrentPassword());
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
    public UserResponse findInactiveUserById(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!user.getStatus().equals(UserStatus.INACTIVE)) {
            throw new ResourceNotFoundException("Inactive user not found");
        }

        return UserMapper.toResponse(user);
    }

}