package com.hermes.market.application.dto.response;

import java.util.ArrayList;
import java.util.List;

public class BrandResponse {

    private Long id;
    private String name;
    private String status;
    private List<ProductResponse> products = new ArrayList<>();

    public BrandResponse(){
    }

    public BrandResponse(Long id, String name, String status, List<ProductResponse> products) {
        this.id = id;
        this.name = name;
        this.status = status;
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

    public List<ProductResponse> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "BrandResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
