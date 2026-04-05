package com.hermes.market.infrastructure.repository;

import com.hermes.market.domain.product.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hermes.market.domain.product.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    boolean existsByCategoryId(Long id);

    boolean existsByBrandId(Long id);

    Page<Product> findByStatus(ProductStatus status, Pageable pageable);

}
