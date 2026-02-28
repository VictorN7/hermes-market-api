package com.hermes.market.application.dto.response;

import java.math.BigDecimal;

public class OrderItemResponse {

    private Long id;
    private ProductResponse product;
    private Integer quantity;
    private BigDecimal price;

    public OrderItemResponse(){
    }

    public OrderItemResponse(Long id, ProductResponse product, Integer quantity, BigDecimal price) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }


    public Long getId() {
        return id;
    }

    public ProductResponse getProduct() {
        return product;
    }


    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "OrderItemResponse{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
