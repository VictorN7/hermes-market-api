package com.hermes.market.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hermes.market.domain.order.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
