package com.hermes.market.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hermes.market.domain.order.Order;
import com.hermes.market.infrastructure.repository.OrderRepository;

@Service
public class OrderService {

	private OrderRepository orderRepository;
	
	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	public List<Order> findAll(){
		return orderRepository.findAll();
	}
	
	public Order findById(Long id) {
		return orderRepository.findById(id).orElseThrow();
	}
}
