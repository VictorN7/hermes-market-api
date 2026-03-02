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
                category.getStatus().name(), category.getCreatedAt());
    }
}
