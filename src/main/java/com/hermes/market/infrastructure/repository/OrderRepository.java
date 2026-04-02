package com.hermes.market.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hermes.market.domain.order.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

    List<Order> findByUserId (Long id);

    boolean existsByUserId(Long id);

    boolean existsByAddressId(Long id);

}
