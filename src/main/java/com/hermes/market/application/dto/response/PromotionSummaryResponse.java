package com.hermes.market.application.dto.response;

import java.math.BigDecimal;

public class PromotionSummaryResponse {

    private String name;
    private String type;
    private BigDecimal promotionalPrice;
    private Integer minQuantity;
    private BigDecimal discountedUnitPrice;

    public PromotionSummaryResponse(){

    }
    public PromotionSummaryResponse(String name, String type, BigDecimal promotionalPrice,
                                    Integer minQuantity, BigDecimal discountedUnitPrice) {
        this.name = name;
        this.type = type;
        this.promotionalPrice = promotionalPrice;
        this.minQuantity = minQuantity;
        this.discountedUnitPrice = discountedUnitPrice;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getPromotionalPrice() {
        return promotionalPrice;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public BigDecimal getDiscountedUnitPrice() {
        return discountedUnitPrice;
    }

    @Override
    public String toString() {
        return "PromotionSummaryResponse{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", promotionalPrice=" + promotionalPrice +
                ", minQuantity=" + minQuantity +
                ", discountedUnitPrice=" + discountedUnitPrice +
                '}';
    }
}
