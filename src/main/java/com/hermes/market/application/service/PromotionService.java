package com.hermes.market.application.service;

import com.hermes.market.application.dto.request.PromotionRequest;
import com.hermes.market.application.dto.response.PromotionResponse;
import com.hermes.market.application.exception.ResourceNotFoundException;
import com.hermes.market.application.mapper.PromotionMapper;
import com.hermes.market.domain.product.PromotionType;
import com.hermes.market.infrastructure.repository.PromotionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PromotionService {

    private final PromotionRepository promotionRepository;

    public PromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public List<PromotionResponse> findAll(){
        return promotionRepository.findAll().stream().map(PromotionMapper::toResponse).toList();
    }

    public PromotionResponse findById(Long id){
        return PromotionMapper.toResponse(promotionRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Id not found: "+ id)));
    }

    public PromotionResponse createPromotion(PromotionRequest promotionRequest){

        return PromotionMapper.toResponse(promotionRepository.save(PromotionMapper.toCreate(promotionRequest,
                PromotionType.valueOf(promotionRequest.getType()))));
    }

}
