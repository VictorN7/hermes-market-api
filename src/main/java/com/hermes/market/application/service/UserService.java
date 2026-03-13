package com.hermes.market.application.service;

import java.util.List;

import com.hermes.market.application.dto.request.UserRequest;
import com.hermes.market.application.dto.response.UserResponse;
import com.hermes.market.application.exception.ResourceNotFoundException;
import com.hermes.market.application.mapper.UserMapper;
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

}