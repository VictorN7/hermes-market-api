package com.hermes.market.application.mapper;

import com.hermes.market.application.dto.request.PromotionRequest;
import com.hermes.market.application.dto.response.PromotionResponse;
import com.hermes.market.application.dto.response.PromotionSummaryResponse;
import com.hermes.market.domain.product.Promotion;
import com.hermes.market.domain.product.PromotionType;

public class PromotionMapper {

    private PromotionMapper(){
    }

    public static PromotionResponse toResponse(Promotion promotion){
        return new PromotionResponse(promotion.getId(), promotion.getName(), promotion.getStartDate(), promotion.getEndDate(),
                promotion.getStatus().name(), promotion.getType().name(), promotion.getDiscountPercentage(), promotion.getMinQuantity());
    }

    public static PromotionSummaryResponse toSummary(Promotion promotion){
        return new PromotionSummaryResponse(promotion.getName(), promotion.getType().name(),
                promotion.getDiscountPercentage(), promotion.getMinQuantity());
    }

    public static Promotion toCreate(PromotionRequest promotionRequest, PromotionType promotionType){
        return new Promotion(promotionRequest.getName(), promotionRequest.getStartDate(), promotionRequest.getEndDate(), promotionType,
                promotionRequest.getDiscountPercentage(),promotionRequest.getMinQuantity());
    }
}
