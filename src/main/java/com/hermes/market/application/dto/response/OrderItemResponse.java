package com.hermes.market.application.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hermes.market.domain.order.Order;
import com.hermes.market.domain.product.Product;
import jakarta.persistence.*;

import java.math.BigDecimal;

public class OrderItemResponse {

    private Long id;
    private ProductResponse product;
    private OrderResponse order;
    private Integer quantity;
    private BigDecimal price;

    public OrderItemResponse(){
    }

    public OrderItemResponse(Long id, ProductResponse product, OrderResponse order, Integer quantity, BigDecimal price) {
        this.id = id;
        this.product = product;
        this.order = order;
        this.quantity = quantity;
        this.price = price;
    }


    public Long getId() {
        return id;
    }

    public ProductResponse getProduct() {
        return product;
    }

    public OrderResponse getOrder() {
        return order;
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
                ", order=" + order +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
