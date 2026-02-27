package com.hermes.market.application.dto.response;

import java.math.BigDecimal;

public class ProductSummaryResponse {

    private Long id;
    private String name;
    private BigDecimal price;
    private String imgUrl;
    private String status;
    private String brand;

    public ProductSummaryResponse(){

    }

    public ProductSummaryResponse(Long id, String name, BigDecimal price, String imgUrl, String status, String brand) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
        this.status = status;
        this.brand = brand;
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

    @Override
    public String toString() {
        return "ProductSummaryResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", imgUrl='" + imgUrl + '\'' +
                ", status='" + status + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
