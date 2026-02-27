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
    private List<ProductResponse> products = new ArrayList<>();

    public CategoryResponse(){
    }

    public CategoryResponse(Long id, String name, String status, Instant createdAt, List<ProductResponse> products) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.createdAt = createdAt;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public List<ProductResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponse> products) {
        this.products = products;
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
