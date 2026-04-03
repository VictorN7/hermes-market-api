package com.hermes.market.infrastructure.repository;


import com.hermes.market.domain.product.CategoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hermes.market.domain.product.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

    boolean existsByNameIgnoreCase(String name);

    List<Category> findByStatus(CategoryStatus status);

}
