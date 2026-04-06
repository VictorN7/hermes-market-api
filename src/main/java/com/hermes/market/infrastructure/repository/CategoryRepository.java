package com.hermes.market.infrastructure.repository;


import com.hermes.market.domain.product.CategoryStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hermes.market.domain.product.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

    boolean existsByNameIgnoreCase(String name);

    Page<Category> findByStatus(Integer status, Pageable pageable);

}
