package com.hermes.market.application.dto.response;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal promotionalPrice;
    private Integer quantityInStock;
    private String imgUrl;
    private String status;
    private Instant createdAt;
    private String category;
    private String brand;
    private List<PromotionResponse> promotions = new ArrayList<>();

    public ProductResponse(){
    }

    public ProductResponse(Long id, String name, String description, BigDecimal price, BigDecimal promotionalPrice, Integer quantityInStock,
                           String imgUrl, String status, Instant createdAt, String category, String brand, List<PromotionResponse> promotions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.imgUrl = imgUrl;
        this.status = status;
        this.createdAt = createdAt;
        this.category = category;
        this.brand = brand;
        this.promotions = promotions;
        this.promotionalPrice = promotionalPrice;
    }

    public List<PromotionResponse> getPromotions() {
        return promotions;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getCategory() {
        return category;
    }

    public String getBrand() {
        return brand;
    }

    public BigDecimal getPromotionalPrice() {
        return promotionalPrice;
    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", promotionalPrice=" + promotionalPrice +
                ", quantityInStock=" + quantityInStock +
                ", imgUrl='" + imgUrl + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", promotions=" + promotions +
                '}';
    }
}
