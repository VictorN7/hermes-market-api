package com.hermes.market.application.dto.response;

import java.math.BigDecimal;

public class ProductDetailResponse {

    private final Long id;
    private final String name;
    private final String description;
    private final Integer quantityInStock;
    private final BigDecimal price;
    private final String imgUrl;
    private final String status;
    private final String category;
    private final String brand;

    public ProductDetailResponse(Long id, String name, String description,
                                 Integer quantityInStock, BigDecimal price, String imgUrl,
                                 String status, String category, String brand) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantityInStock = quantityInStock;
        this.price = price;
        this.imgUrl = imgUrl;
        this.status = status;
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

    public Integer getQuantityInStock() {
        return quantityInStock;
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

    public String getCategory() {
        return category;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return "ProductDetailResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", quantityInStock=" + quantityInStock +
                ", price=" + price +
                ", imgUrl='" + imgUrl + '\'' +
                ", status='" + status + '\'' +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
