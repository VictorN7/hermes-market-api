package com.hermes.market.application.dto.response;


import java.time.Instant;

public class BrandDetailResponse {

    private Long id;
    private String name;
    private String status;
    private Instant createdAt;

    public BrandDetailResponse(){
    }

    public BrandDetailResponse(Long id, String name, String status, Instant createdAt) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.createdAt = createdAt;
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

    @Override
    public String toString() {
        return "BrandDetailResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
