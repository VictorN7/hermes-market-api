package com.hermes.market.application.mapper;

import com.hermes.market.application.dto.response.ProductDetailResponse;
import com.hermes.market.application.dto.response.ProductResponse;
import com.hermes.market.application.dto.response.ProductSummaryResponse;
import com.hermes.market.domain.product.Product;

public class ProductMapper {

    private ProductMapper(){
    }

    public static ProductSummaryResponse toSummary(Product product){
        return new ProductSummaryResponse(product.getId(), product.getName(),product.getPrice(),
                product.getImgUrl(), product.getStatus().name(), product.getBrand().getName());
    }

    public static ProductDetailResponse toDetail(Product product){
        return new ProductDetailResponse(product.getId(), product.getName(), product.getDescription()
                ,product.getQuantityInStock(), product.getPrice(), product.getImgUrl(),
                product.getStatus().name(), product.getCategory().getName(), product.getBrand().getName());
    }

    public static ProductResponse toResponse(Product product){
        return new ProductResponse(product.getId(), product.getName(), product.getDescription()
                ,product.getPrice(), product.getQuantityInStock(), product.getImgUrl(),
                product.getStatus().name(), product.getCreatedAt(), product.getCategory().getName(), product.getBrand().getName());
    }
}
