package com.hermes.market.web.controller;

import java.util.List;

import com.hermes.market.application.dto.response.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hermes.market.application.service.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

	private final OrderService orderService;
	
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping
	public ResponseEntity<List<OrderResponse>> findAll(){
		return ResponseEntity.ok().body(orderService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderResponse> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(orderService.findById(id));
	}
}