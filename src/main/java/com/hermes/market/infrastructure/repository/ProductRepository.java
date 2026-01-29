package com.hermes.market.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hermes.market.domain.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
