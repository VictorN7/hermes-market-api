package com.hermes.market.web.controller;

import java.util.List;

import com.hermes.market.application.dto.response.OrderResponse;
import com.hermes.market.application.dto.response.OrderSummaryResponse;
import com.hermes.market.application.dto.response.UserResponse;
import com.hermes.market.application.service.OrderService;
import org.apache.coyote.Response;
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
	private final OrderService orderService;
	
	public UserController(UserService userService, OrderService orderService) {
		this.userService = userService;
		this.orderService = orderService;
	}

	@GetMapping
	public ResponseEntity<List<UserResponse>> findAll(){
		return ResponseEntity.ok().body(userService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(userService.findById(id));
	}

	@GetMapping("/{id}/orders")
	public ResponseEntity<List<OrderSummaryResponse>> findOrdersByUser(@PathVariable Long id){
		return ResponseEntity.ok().body(orderService.findOrdersByUser(id));
	}
}
