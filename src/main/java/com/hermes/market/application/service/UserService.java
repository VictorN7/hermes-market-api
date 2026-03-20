package com.hermes.market.application.service;

import java.util.List;

import com.hermes.market.application.dto.request.UserPasswordRequest;
import com.hermes.market.application.dto.request.UserRequest;
import com.hermes.market.application.dto.request.UserUpdateRequest;
import com.hermes.market.application.dto.response.UserResponse;
import com.hermes.market.application.exception.ResourceNotFoundException;
import com.hermes.market.application.mapper.UserMapper;
import com.hermes.market.domain.user.User;
import org.springframework.stereotype.Service;

import com.hermes.market.infrastructure.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<UserResponse> findAll(){
		return userRepository.findAll().stream().map(UserMapper::toResponse).toList();
	}
	
	public UserResponse findById(Long id) {
		return UserMapper.toResponse(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found")));
	}

	public UserResponse createUser(UserRequest userRequest){
		return UserMapper.toResponse(userRepository.save(UserMapper.toCreate(userRequest)));
	}

	public UserResponse updateUser(Long userId, UserUpdateRequest userUpdateRequest){

		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found!"));
		user.updateUser(userUpdateRequest.getName(), userUpdateRequest.getEmail(), userUpdateRequest.getBirthDate());
		userRepository.save(user);

		return UserMapper.toResponse(user);
	}

	public void updatePassword(Long userId, UserPasswordRequest userPasswordRequest){

		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found!"));
		user.updatePassword(userPasswordRequest.getNewPassword(), userPasswordRequest.getConfirmPassword(), userPasswordRequest.getCurrentPassword());
		userRepository.save(user);
	}

	public void deactivateUser(Long userId){

		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found!"));
		user.deactivate();
		userRepository.save(user);
	}

	public void activateUser(Long userId){

		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found!"));
		user.activate();
		userRepository.save(user);
	}

	public void blockUser(Long userId){

		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found!"));
		user.block();
		userRepository.save(user);
	}

	public void unlockUser(Long userId){

		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found!"));
		user.unlock();
		userRepository.save(user);
	}

}