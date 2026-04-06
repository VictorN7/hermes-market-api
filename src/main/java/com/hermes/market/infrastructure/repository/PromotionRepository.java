package com.hermes.market.infrastructure.repository;

import com.hermes.market.domain.product.Promotion;
import com.hermes.market.domain.product.PromotionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    Page<Promotion> findByStatus(Integer status, Pageable pageable);

}
