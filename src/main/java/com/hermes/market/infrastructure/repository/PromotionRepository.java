package com.hermes.market.infrastructure.repository;

import com.hermes.market.domain.product.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

}
