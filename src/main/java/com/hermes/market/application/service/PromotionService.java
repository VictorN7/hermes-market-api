package com.hermes.market.application.service;

import com.hermes.market.domain.product.Product;
import com.hermes.market.domain.product.Promotion;
import com.hermes.market.infrastructure.repository.PromotionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionService {

    private final PromotionRepository promotionRepository;

    public PromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public List<Promotion> findAll(){
        return promotionRepository.findAll();
    }

    public Promotion findById(Long id){
        return promotionRepository.findById(id).orElseThrow( () -> new RuntimeException("Id not found: "+ id));
    }

}
