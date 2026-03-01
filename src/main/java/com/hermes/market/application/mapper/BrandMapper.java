package com.hermes.market.application.mapper;

import com.hermes.market.application.dto.response.BrandMenuResponse;
import com.hermes.market.application.dto.response.ProductResponse;
import com.hermes.market.domain.product.Brand;
import com.hermes.market.domain.product.Product;

import java.util.List;
public class BrandMapper {


    private BrandMapper(){
    }

    public static BrandMenuResponse toResponse(Brand brand){
        return new BrandMenuResponse(brand.getId(), brand.getName());
    }

    public static List<ProductResponse> convertProducts(List<Product> products){
        if (products == null) return List.of();
        return products.stream().map(ProductMapper::toResponse).toList();
    }
}
