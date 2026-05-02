package com.hermes.market.application.service;

import com.hermes.market.application.dto.request.OrderItemRequest;
import com.hermes.market.application.dto.request.OrderItemUpdateQuantityRequest;
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
import com.hermes.market.domain.order.OrderItem;
import com.hermes.market.domain.order.PaymentMethod;
import com.hermes.market.domain.product.Product;
import com.hermes.market.domain.user.Address;
import com.hermes.market.domain.user.User;
import com.hermes.market.infrastructure.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public Page<OrderResponse> findAll(Pageable pageable) {
        Page<Order> orders = orderRepository.findAll(pageable);
        return orders.map(OrderMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public OrderResponse findById(Long id) {
        return OrderMapper.toResponse(orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found")));
    }

    @Transactional(readOnly = true)
    public Page<OrderSummaryResponse> findOrdersByUser(Long id, Pageable pageable) {

        if(!userRepository.existsById(id)){
            throw new ResourceNotFoundException("User not found");
        }

        Page<Order> orders = orderRepository.findByUserId(id, pageable);
        return orders.map(OrderMapper::toSummary);
    }

    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest) {

        Address address = addressRepository.findById(orderRequest.getAddressId()).orElseThrow(() -> new ResourceNotFoundException("Address not found!"));
        User user = userRepository.findById(orderRequest.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!address.getUser().getId().equals(user.getId()))
            throw new BusinessException("Address does not belong to this user");

        return OrderMapper.toResponse(orderRepository.save(OrderMapper.toCreate(
                PaymentMethod.valueOf(orderRequest.getPayment()),
                DeliveryMethod.valueOf(orderRequest.getDelivery()),
                user,
                address)));
    }

    @Transactional
    public OrderItemResponse createOrderItem(Long orderId, OrderItemRequest orderItemRequest) {

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        Product product = productRepository.findById(orderItemRequest.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        order.addItem(product, orderItemRequest.getQuantity());
        orderRepository.save(order);

        return OrderItemMapper.toResponse(order.getOrderItems().get(order.getOrderItems().size() - 1));
    }

    @Transactional
    public OrderResponse updateOrderItemQuantity(Long orderId, Long itemId, OrderItemUpdateQuantityRequest request) {

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        order.updateItemQuantity(itemId, request.getQuantity());

        return OrderMapper.toResponse(orderRepository.save(order));
    }

    @Transactional
    public OrderResponse cancelOrder(Long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        order.cancel();
        return OrderMapper.toResponse(orderRepository.save(order));
    }

    @Transactional
    public OrderResponse payOrder(Long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        order.pay();

        for (OrderItem item : order.getOrderItems()) {
            productRepository.save(item.getProduct());
        }
        return OrderMapper.toResponse(orderRepository.save(order));
    }

    @Transactional
    public OrderResponse shipOrder(Long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        order.ship();

        return OrderMapper.toResponse(orderRepository.save(order));
    }

    @Transactional
    public OrderResponse deliverOrder(Long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        order.deliver();

        return OrderMapper.toResponse(orderRepository.save(order));
    }

    @Transactional
    public OrderResponse deleteOrderItem(Long orderId, Long itemId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        order.deleteOrderItem(itemId);

        return OrderMapper.toResponse(orderRepository.save(order));
    }

}