package com.hermes.market.infrastructure.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.hermes.market.domain.product.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	
}
