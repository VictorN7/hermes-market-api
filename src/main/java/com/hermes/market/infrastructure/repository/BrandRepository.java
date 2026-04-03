package com.hermes.market.infrastructure.repository;

import com.hermes.market.domain.product.Brand;
import com.hermes.market.domain.product.BrandStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    boolean existsByNameIgnoreCase(String name);

    List<Brand> findByStatus(BrandStatus status);

}
