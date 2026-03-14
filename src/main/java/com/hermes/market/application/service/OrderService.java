package com.hermes.market.application.service;

import java.util.List;

import com.hermes.market.application.dto.request.OrderRequest;
import com.hermes.market.application.dto.response.OrderResponse;
import com.hermes.market.application.dto.response.OrderSummaryResponse;
import com.hermes.market.application.exception.ResourceNotFoundException;
import com.hermes.market.application.mapper.OrderMapper;
import com.hermes.market.domain.order.DeliveryMethod;
import com.hermes.market.domain.order.PaymentMethod;
import com.hermes.market.infrastructure.repository.AddressRepository;
import com.hermes.market.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

import com.hermes.market.infrastructure.repository.OrderRepository;

@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final UserRepository userRepository;
	private final AddressRepository addressRepository;

	public OrderService(OrderRepository orderRepository, UserRepository userRepository, AddressRepository addressRepository) {
		this.orderRepository = orderRepository;
		this.userRepository = userRepository;
		this.addressRepository = addressRepository;
	}

	public List<OrderResponse> findAll(){
		return orderRepository.findAll().stream().map(OrderMapper::toResponse).toList();
	}
	
	public OrderResponse findById(Long id) {
		return OrderMapper.toResponse(orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found!")));
	}

	public List<OrderSummaryResponse> findOrdersByUser(Long id){
		return orderRepository.findByUserId(id).stream().map(OrderMapper::toSummary).toList();
	}

	public OrderResponse createOrder(OrderRequest orderRequest){
		return OrderMapper.toResponse(orderRepository.save(OrderMapper.toCreate(
				PaymentMethod.valueOf(orderRequest.getPayment()),
				DeliveryMethod.valueOf(orderRequest.getDelivery()),
				userRepository.findById(orderRequest.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found!")),
				addressRepository.findById(orderRequest.getAddressId()).orElseThrow(() -> new ResourceNotFoundException("Address not found!")))));
	}
}
