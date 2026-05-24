package com.hermes.market.infrastructure.repository;

import com.hermes.market.domain.product.Promotion;
import com.hermes.market.domain.product.PromotionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    Page<Promotion> findByStatus(Integer status, Pageable pageable);

    Optional<Promotion> findByIdAndStatus(Long id, Integer status);

}
