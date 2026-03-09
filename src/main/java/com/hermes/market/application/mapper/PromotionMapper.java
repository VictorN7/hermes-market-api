package com.hermes.market.application.mapper;

import com.hermes.market.application.dto.response.PromotionResponse;
import com.hermes.market.domain.product.Promotion;

public class PromotionMapper {

    private PromotionMapper(){
    }

    public static PromotionResponse toResponse(Promotion promotion){

        return new PromotionResponse(promotion.getId(), promotion.getName(), promotion.getStartDate(), promotion.getEndDate(),
                promotion.getStatus().name(), promotion.getType().name(), promotion.getPromotionalPrice(), promotion.getMinQuantity(),
                promotion.getDiscountedUnitPrice());

    }
}
