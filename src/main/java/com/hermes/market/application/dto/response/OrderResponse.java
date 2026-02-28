package com.hermes.market.application.dto.response;

import com.hermes.market.domain.order.OrderItem;
import com.hermes.market.domain.user.User;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderResponse {

    private Long id;
    private UserResponse user;
    private List<OrderItemResponse> orderItens = new ArrayList<>();
    private String status;
    private BigDecimal totalPrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String payment;
    private String delivery;

    public OrderResponse(){
    }

    public OrderResponse(Long id, UserResponse user, List<OrderItemResponse> orderItens, String status, BigDecimal totalPrice,
                         LocalDateTime createdAt, LocalDateTime updatedAt, String payment, String delivery) {
        this.id = id;
        this.user = user;
        this.orderItens = orderItens;
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

    public List<OrderItemResponse> getOrderItens() {
        return orderItens;
    }

    public String getStatus() {
        return status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
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
