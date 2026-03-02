package com.hermes.market.application.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderSummaryResponse {

    private Long id;
    private String status;
    private BigDecimal totalPrice;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private LocalDateTime createdAt;
    private String payment;
    private String delivery;

    public OrderSummaryResponse(){
    }

    public OrderSummaryResponse(Long id, String status, BigDecimal totalPrice, LocalDateTime createdAt, String payment, String delivery) {
        this.id = id;
        this.status = status;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.payment = payment;
        this.delivery = delivery;
    }

    public Long getId() {
        return id;
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

    public String getPayment() {
        return payment;
    }

    public String getDelivery() {
        return delivery;
    }

    @Override
    public String toString() {
        return "OrderSummaryResponse{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", totalPrice=" + totalPrice +
                ", createdAt=" + createdAt +
                ", payment='" + payment + '\'' +
                ", delivery='" + delivery + '\'' +
                '}';
    }
}
