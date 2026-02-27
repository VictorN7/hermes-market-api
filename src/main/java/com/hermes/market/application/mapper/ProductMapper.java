package com.hermes.market.application.mapper;

import com.hermes.market.application.dto.response.ProductDetailResponse;
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
}
