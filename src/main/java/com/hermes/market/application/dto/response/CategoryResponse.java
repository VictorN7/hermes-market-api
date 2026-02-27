package com.hermes.market.application.dto.response;

import com.hermes.market.domain.product.CategoryStatus;
import com.hermes.market.domain.product.Product;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class CategoryResponse {

    private Long id;
    private String name;
    private String status;
    private Instant createdAt;
    private List<ProductSummaryResponse> products = new ArrayList<>();

    public CategoryResponse(){
    }

    public CategoryResponse(Long id, String name, String status, Instant createdAt, List<ProductSummaryResponse> products) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.createdAt = createdAt;
        this.products = products;
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public List<ProductSummaryResponse> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "CategoryResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
