package com.hermes.market.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hermes.market.domain.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
