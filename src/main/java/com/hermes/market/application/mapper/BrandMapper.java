package com.hermes.market.application.mapper;

import com.hermes.market.application.dto.response.BrandResponse;
import com.hermes.market.application.dto.response.ProductResponse;
import com.hermes.market.domain.product.Brand;
import com.hermes.market.domain.product.Product;

import java.util.List;
public class BrandMapper {


    private BrandMapper(){
    }

    public static BrandResponse toResponse(Brand brand){
        return new BrandResponse(brand.getId(), brand.getName(), brand.getStatus().name(), convertProducts(brand.getProducts()));
    }

    public static List<ProductResponse> convertProducts(List<Product> products){
        if (products == null) return List.of();
        return products.stream().map(ProductMapper::toResponse).toList();
    }
}
