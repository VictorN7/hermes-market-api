package com.hermes.market.application.service;

import com.hermes.market.application.dto.request.PromotionRequest;
import com.hermes.market.application.dto.response.PromotionResponse;
import com.hermes.market.application.exception.ResourceNotFoundException;
import com.hermes.market.application.mapper.PromotionMapper;
import com.hermes.market.domain.product.Product;
import com.hermes.market.domain.product.Promotion;
import com.hermes.market.domain.product.PromotionStatus;
import com.hermes.market.domain.product.PromotionType;
import com.hermes.market.infrastructure.repository.ProductRepository;
import com.hermes.market.infrastructure.repository.PromotionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PromotionService {

    private final PromotionRepository promotionRepository;
    private final ProductRepository productRepository;

    public PromotionService(PromotionRepository promotionRepository, ProductRepository productRepository) {
        this.promotionRepository = promotionRepository;
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public Page<PromotionResponse> findAll(Pageable pageable) {
        Page<Promotion> promotions = promotionRepository.findAll(pageable);
        return promotions.map(PromotionMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public PromotionResponse findById(Long id){
        return PromotionMapper.toResponse(promotionRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Id not found: "+ id)));
    }

    @Transactional
    public PromotionResponse createPromotion(PromotionRequest promotionRequest){
        return PromotionMapper.toResponse(promotionRepository.save(PromotionMapper.toCreate(promotionRequest,
                PromotionType.valueOf(promotionRequest.getType()))));
    }

    @Transactional
    public PromotionResponse insertProduct(Long productId, Long promotionId){
        Promotion promotion = promotionRepository.findById(promotionId).orElseThrow(() -> new ResourceNotFoundException("Promotion not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        promotion.addProduct(product);
        promotionRepository.save(promotion);
        return PromotionMapper.toResponse(promotion);
    }

    @Transactional
    public void deactivatePromotion(Long promotionId){
        Promotion promotion = promotionRepository.findById(promotionId).orElseThrow(() -> new ResourceNotFoundException("Promotion not found"));
        promotion.deactivate();
        promotionRepository.save(promotion);
    }

    @Transactional
    public void activatePromotion(Long promotionId){
        Promotion promotion = promotionRepository.findById(promotionId).orElseThrow(() -> new ResourceNotFoundException("Promotion not found"));
        promotion.activate();
        promotionRepository.save(promotion);
    }

    @Transactional
    public PromotionResponse deleteProduct(Long promotionId, Long productId){
        Promotion promotion = promotionRepository.findById(promotionId)
                .orElseThrow(() -> new ResourceNotFoundException("Promotion not found"));

        promotion.deleteProduct(productId);

        return PromotionMapper.toResponse(promotionRepository.save(promotion));
    }

    @Transactional(readOnly = true)
    public Page<PromotionResponse> findInactivePromotions(Pageable pageable){
        Page<Promotion> promotions = promotionRepository.findByStatus(PromotionStatus.INACTIVE.getCode(), pageable);
        return promotions.map(PromotionMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public PromotionResponse findInactivePromotionById(Long promotionId){
        Promotion promotion = promotionRepository.findById(promotionId).orElseThrow(() -> new ResourceNotFoundException("Promotion not found"));
        if(!PromotionStatus.INACTIVE.equals(promotion.getStatus())){
            throw new ResourceNotFoundException("Inactive promotion not found");
        }
        return PromotionMapper.toResponse(promotion);
    }

}
