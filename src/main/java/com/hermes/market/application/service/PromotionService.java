package com.hermes.market.application.service;

import com.hermes.market.application.dto.request.PromotionRequest;
import com.hermes.market.application.dto.response.ProductResponse;
import com.hermes.market.application.dto.response.PromotionResponse;
import com.hermes.market.application.exception.BusinessException;
import com.hermes.market.application.exception.ResourceNotFoundException;
import com.hermes.market.application.mapper.ProductMapper;
import com.hermes.market.application.mapper.PromotionMapper;
import com.hermes.market.domain.product.Product;
import com.hermes.market.domain.product.Promotion;
import com.hermes.market.domain.product.PromotionStatus;
import com.hermes.market.domain.product.PromotionType;
import com.hermes.market.infrastructure.repository.ProductRepository;
import com.hermes.market.infrastructure.repository.PromotionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    private Promotion findPromotionByIdOrThrow (Long id, PromotionStatus status){
        return promotionRepository.findByIdAndStatus(id, status.getCode()).orElseThrow( () -> new ResourceNotFoundException("Promotion not found"));
    }

    private Promotion findPromotionByIdOrThrow (Long id){
        return promotionRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Promotion not found"));
    }

    @Transactional(readOnly = true)
    public Page<PromotionResponse> findAll(Pageable pageable) {
        return promotionRepository.findByStatus(PromotionStatus.ACTIVE.getCode(), pageable).map(PromotionMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public PromotionResponse findById(Long id){
        return PromotionMapper.toResponse(findPromotionByIdOrThrow(id, PromotionStatus.ACTIVE));
    }

    @Transactional
    public PromotionResponse createPromotion(PromotionRequest promotionRequest){

    if (promotionRepository.existsByNameIgnoreCaseAndStatus(promotionRequest.getName(), PromotionStatus.ACTIVE.getCode())) {
        throw new BusinessException("Promotion name already exists");
    }
        return PromotionMapper.toResponse(promotionRepository.save(PromotionMapper.toCreate(promotionRequest,
                PromotionType.valueOf(promotionRequest.getType()))));
    }

    @Transactional
    public Page<ProductResponse> insertProduct(Long productId, Long promotionId, Pageable pageable){
        Promotion promotion = findPromotionByIdOrThrow(promotionId, PromotionStatus.ACTIVE);
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        promotion.addProduct(product);
        promotionRepository.save(promotion);

        return productRepository.findByPromotionsId(promotion.getId(), pageable).map(ProductMapper::toResponse);
    }

    public Page<ProductResponse> findProductsByPromotion(Long promotionId, Pageable pageable) {
        return productRepository.findByPromotionsId(promotionId,pageable).map(ProductMapper::toResponse);
    }

    @Transactional
    public void deactivatePromotion(Long promotionId){
        Promotion promotion = findPromotionByIdOrThrow(promotionId);
        promotion.deactivate();
        promotionRepository.save(promotion);
    }

    @Transactional
    public void activatePromotion(Long promotionId){
        Promotion promotion = findPromotionByIdOrThrow(promotionId);
        promotion.activate();
        promotionRepository.save(promotion);
    }

    @Transactional
    public PromotionResponse deleteProduct(Long promotionId, Long productId){
        Promotion promotion = findPromotionByIdOrThrow(promotionId, PromotionStatus.ACTIVE);

        promotion.deleteProduct(productId);

        return PromotionMapper.toResponse(promotionRepository.save(promotion));
    }

    @Transactional(readOnly = true)
    public Page<PromotionResponse> findAllInactive(Pageable pageable){
        return promotionRepository.findByStatus(PromotionStatus.INACTIVE.getCode(), pageable).map(PromotionMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public PromotionResponse findInactiveById(Long promotionId){
        Promotion promotion = findPromotionByIdOrThrow(promotionId, PromotionStatus.INACTIVE);
        return PromotionMapper.toResponse(promotion);
    }

}
