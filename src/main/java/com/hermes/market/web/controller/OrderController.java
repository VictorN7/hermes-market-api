package com.hermes.market.web.controller;

import java.util.List;

import com.hermes.market.application.dto.request.OrderItemRequest;
import com.hermes.market.application.dto.request.OrderItemUpdateQuantityRequest;
import com.hermes.market.application.dto.request.OrderRequest;
import com.hermes.market.application.dto.response.OrderItemResponse;
import com.hermes.market.application.dto.response.OrderResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@PostMapping
	public ResponseEntity<OrderResponse> createOrder(@RequestBody @Valid OrderRequest orderRequest){
		return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderRequest));
	}

	@PostMapping("/{id}/items")
	public ResponseEntity<OrderItemResponse> createOrderItem(@PathVariable Long id, @RequestBody @Valid OrderItemRequest orderItemRequest){
		return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrderItem(id,orderItemRequest));
	}

	@PatchMapping("/{id}/items/{itemId}/quantity")
	public ResponseEntity<OrderResponse> updateOrderItemQuantity(@PathVariable Long id,
																 @PathVariable Long itemId,
																 @RequestBody @Valid OrderItemUpdateQuantityRequest request){
		return ResponseEntity.ok().body(orderService.updateOrderItemQuantity(id, itemId, request));
	}

}