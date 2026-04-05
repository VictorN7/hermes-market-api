package com.hermes.market.infrastructure.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hermes.market.domain.order.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

    Page<Order> findByUserId (Long id, Pageable pageable);

    boolean existsByUserId(Long id);

    boolean existsByAddressId(Long id);

}
