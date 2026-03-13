package com.hermes.market.application.dto.response;

import java.math.BigDecimal;
import java.time.Instant;

public class PromotionResponse {

    private Long id;
    private String name;
    private Instant startDate;
    private Instant endDate;
    private String status;
    private String type;
    private BigDecimal promotionalPrice;
    private Integer minQuantity;

    public PromotionResponse(){

    }

    public PromotionResponse(Long id, String name, Instant startDate, Instant endDate,
                             String status, String type, BigDecimal promotionalPrice,
                             Integer minQuantity) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.type = type;
        this.promotionalPrice = promotionalPrice;
        this.minQuantity = minQuantity;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public String getStatus() {
        return status;
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


    @Override
    public String toString() {
        return "PromotionResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", promotionalPrice=" + promotionalPrice +
                ", minQuantity=" + minQuantity +
                '}';
    }
}
