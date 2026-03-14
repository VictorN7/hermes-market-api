package com.hermes.market.application.service;

import java.util.List;

import com.hermes.market.application.dto.request.OrderItemRequest;
import com.hermes.market.application.dto.request.OrderRequest;
import com.hermes.market.application.dto.response.OrderItemResponse;
import com.hermes.market.application.dto.response.OrderResponse;
import com.hermes.market.application.dto.response.OrderSummaryResponse;
import com.hermes.market.application.exception.BusinessException;
import com.hermes.market.application.exception.ResourceNotFoundException;
import com.hermes.market.application.mapper.OrderItemMapper;
import com.hermes.market.application.mapper.OrderMapper;
import com.hermes.market.domain.order.DeliveryMethod;
import com.hermes.market.domain.order.Order;
import com.hermes.market.domain.order.PaymentMethod;
import com.hermes.market.domain.product.Product;
import com.hermes.market.domain.user.Address;
import com.hermes.market.domain.user.User;
import com.hermes.market.infrastructure.repository.*;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final UserRepository userRepository;
	private final AddressRepository addressRepository;
	private final ProductRepository productRepository;

	public OrderService(OrderRepository orderRepository, UserRepository userRepository,
						AddressRepository addressRepository, ProductRepository productRepository) {
		this.orderRepository = orderRepository;
		this.userRepository = userRepository;
		this.addressRepository = addressRepository;
		this.productRepository = productRepository;
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

		Address address = addressRepository.findById(orderRequest.getAddressId()).orElseThrow(() -> new ResourceNotFoundException("Address not found!"));
		User user= userRepository.findById(orderRequest.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));

		if (!address.getUser().getId().equals(user.getId())) throw new BusinessException("Address does not belong to this user");

		return OrderMapper.toResponse(orderRepository.save(OrderMapper.toCreate(
				PaymentMethod.valueOf(orderRequest.getPayment()),
				DeliveryMethod.valueOf(orderRequest.getDelivery()),
				user,
				address)));
	}

	public OrderItemResponse createOrderItem(Long orderId, OrderItemRequest orderItemRequest){

		Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found!"));
		Product product = productRepository.findById(orderItemRequest.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found!"));

		order.addItem(product, orderItemRequest.getQuantity());
		orderRepository.save(order);

		return OrderItemMapper.toResponse(order.getOrderItems().get(order.getOrderItems().size()-1));
	}

}