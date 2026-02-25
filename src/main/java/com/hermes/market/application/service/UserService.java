package com.hermes.market.application.service;

import java.util.List;

import com.hermes.market.application.dto.response.UserResponseDTO;
import com.hermes.market.application.mapper.UserMapper;
import org.springframework.stereotype.Service;

import com.hermes.market.domain.user.User;
import com.hermes.market.infrastructure.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<UserResponseDTO> findAll(){
		return userRepository.findAll().stream().map(UserMapper::toResponse).toList();
	}
	
	public UserResponseDTO findById(Long id) {
		return  UserMapper.toResponse(userRepository.findById(id).orElseThrow( () -> new RuntimeException("User not found")));
	}
}