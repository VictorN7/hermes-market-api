package com.hermes.market.application.dto.response;

import java.math.BigDecimal;

public class PromotionSummaryResponse {

    private String name;
    private String type;
    private BigDecimal discountPercentage;
    private Integer minQuantity;

    public PromotionSummaryResponse(){

    }
    public PromotionSummaryResponse(String name, String type, BigDecimal discountPercentage,
                                    Integer minQuantity) {
        this.name = name;
        this.type = type;
        this.discountPercentage = discountPercentage;
        this.minQuantity = minQuantity;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    @Override
    public String toString() {
        return "PromotionSummaryResponse{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", discountPercentage=" + discountPercentage +
                ", minQuantity=" + minQuantity +
                '}';
    }
}
