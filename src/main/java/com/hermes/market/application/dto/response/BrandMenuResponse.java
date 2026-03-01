package com.hermes.market.application.dto.response;

import java.util.ArrayList;
import java.util.List;

public class BrandMenuResponse {

    private Long id;
    private String name;

    public BrandMenuResponse(){
    }

    public BrandMenuResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "BrandResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
