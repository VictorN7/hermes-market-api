package com.hermes.market.web.controller;

import java.util.List;

import com.hermes.market.application.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hermes.market.application.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<List<UserResponse>> findAll(){
		return ResponseEntity.ok().body(userService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(userService.findById(id));
	}
}
