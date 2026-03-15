package com.hermes.market.application.dto.response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductSummaryResponse {

    private Long id;
    private String name;
    private BigDecimal price;
    private BigDecimal promotionalPrice;
    private String imgUrl;
    private String status;
    private String brand;
    private List<PromotionSummaryResponse> promotions = new ArrayList<>();

    public ProductSummaryResponse(){
    }

    public ProductSummaryResponse(Long id, String name, BigDecimal price, BigDecimal promotionalPrice, String imgUrl,
                                  String status, String brand, List<PromotionSummaryResponse> promotions) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
        this.status = status;
        this.brand = brand;
        this.promotions = promotions;
        this.promotionalPrice = promotionalPrice;
    }

    public List<PromotionSummaryResponse> getPromotions() {
        return promotions;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getStatus() {
        return status;
    }

    public String getBrand() {
        return brand;
    }

    public BigDecimal getPromotionalPrice() {
        return promotionalPrice;
    }

    @Override
    public String toString() {
        return "ProductSummaryResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", promotionalPrice=" + promotionalPrice +
                ", imgUrl='" + imgUrl + '\'' +
                ", status='" + status + '\'' +
                ", brand='" + brand + '\'' +
                ", promotions=" + promotions +
                '}';
    }
}
