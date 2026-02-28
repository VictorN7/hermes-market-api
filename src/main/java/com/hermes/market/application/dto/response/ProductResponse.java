package com.hermes.market.application.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hermes.market.domain.product.Brand;
import com.hermes.market.domain.product.Category;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantityInStock;
    private String imgUrl;
    private String status;
    private Instant createdAt;
    private String category;
    private String brand;

    public ProductResponse(){
    }

    public ProductResponse(Long id, String name, String description, BigDecimal price, Integer quantityInStock,
                           String imgUrl, String status, Instant createdAt, String category, String brand) {
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


    @Override
    public String toString() {
        return "ProductResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantityInStock=" + quantityInStock +
                ", imgUrl='" + imgUrl + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
