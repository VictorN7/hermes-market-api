package com.hermes.market.application.mapper;

import com.hermes.market.application.dto.response.CategoryResponse;
import com.hermes.market.application.dto.response.ProductSummaryResponse;
import com.hermes.market.domain.product.Category;
import com.hermes.market.domain.product.Product;

import java.util.List;

public class CategoryMapper {

    private CategoryMapper(){
    }

    public static CategoryResponse toResponse(Category category){
        return new CategoryResponse(category.getId(), category.getName(),
                category.getStatus().name(), category.getCreatedAt(), convertProducts(category.getProducts()));
    }

    private static List<ProductSummaryResponse> convertProducts(List<Product> products){
        if (products == null) return List.of();

        return products.stream().map(x -> new ProductSummaryResponse(x.getId(), x.getName()
                ,x.getPrice(),x.getImgUrl(), x.getStatus().name(), x.getBrand().getName())).toList();
    }
}
