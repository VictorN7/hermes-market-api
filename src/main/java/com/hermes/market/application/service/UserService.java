package com.hermes.market.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hermes.market.domain.user.User;
import com.hermes.market.infrastructure.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow();
	}
}