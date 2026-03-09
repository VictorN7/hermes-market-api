package com.hermes.market.application.dto.response;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderResponse {

    private Long id;
    private UserResponse user;
    private List<OrderItemResponse> orderItems = new ArrayList<>();
    private String status;
    private BigDecimal totalPrice;
    private Instant createdAt;
    private Instant updatedAt;
    private String payment;
    private String delivery;

    public OrderResponse(){
    }

    public OrderResponse(Long id, UserResponse user, List<OrderItemResponse> orderItems, String status, BigDecimal totalPrice,
                         Instant createdAt, Instant updatedAt, String payment, String delivery) {
        this.id = id;
        this.user = user;
        this.orderItems = orderItems;
        this.status = status;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.payment = payment;
        this.delivery = delivery;
    }

    public Long getId() {
        return id;
    }

    public UserResponse getUser() {
        return user;
    }

    public List<OrderItemResponse> getOrderItems() {
        return orderItems;
    }

    public String getStatus() {
        return status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public String getPayment() {
        return payment;
    }

    public String getDelivery() {
        return delivery;
    }

    @Override
    public String toString() {
        return "OrderResponse{" +
                "id=" + id +
                ", user=" + user +
                ", status=" + status +
                ", totalPrice=" + totalPrice +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", payment='" + payment + '\'' +
                ", delivery='" + delivery + '\'' +
                '}';
    }
}
