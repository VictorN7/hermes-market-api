package com.hermes.market.application.service;

import java.util.List;

import com.hermes.market.application.dto.response.OrderResponse;
import com.hermes.market.application.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import com.hermes.market.infrastructure.repository.OrderRepository;

@Service
public class OrderService {

	private final OrderRepository orderRepository;
	
	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	public List<OrderResponse> findAll(){
		return orderRepository.findAll().stream().map(OrderMapper::toResponse).toList();
	}
	
	public OrderResponse findById(Long id) {
		return OrderMapper.toResponse(orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found!")));
	}
}
