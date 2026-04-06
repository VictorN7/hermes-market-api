package com.hermes.market.infrastructure.repository;

import com.hermes.market.domain.product.Brand;
import com.hermes.market.domain.product.BrandStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    boolean existsByNameIgnoreCase(String name);

    Page<Brand> findByStatus(Integer status, Pageable pageable);

}
