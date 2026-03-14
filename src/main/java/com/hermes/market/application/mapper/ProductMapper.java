package com.hermes.market.application.mapper;

import com.hermes.market.application.dto.request.ProductRequest;
import com.hermes.market.application.dto.response.ProductResponse;
import com.hermes.market.application.dto.response.ProductSummaryResponse;
import com.hermes.market.domain.product.Brand;
import com.hermes.market.domain.product.Category;
import com.hermes.market.domain.product.Product;
import com.hermes.market.domain.product.PromotionStatus;

public class ProductMapper {

    private ProductMapper() {
    }

    public static ProductSummaryResponse toSummary(Product product) {
        return new ProductSummaryResponse(product.getId(), product.getName(), product.getPrice(),
                product.getImgUrl(), product.getStatus().name(), product.getBrand().getName(),
                product.getPromotions().stream().filter(x -> x.getStatus() == PromotionStatus.ACTIVE)
                        .map(PromotionMapper::toSummary).toList());
    }

    public static ProductResponse toResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getDescription()
                , product.getPrice(), product.getQuantityInStock(), product.getImgUrl(),
                product.getStatus().name(), product.getCreatedAt(), product.getCategory().getName(),
                product.getBrand().getName(), product.getPromotions().stream().filter(x -> x.getStatus() == PromotionStatus.ACTIVE)
                .map(PromotionMapper::toResponse).toList());
    }

    public static Product toCreate(ProductRequest productRequest, Category category, Brand brand) {

        return new Product(productRequest.getName(), productRequest.getDescription(), productRequest.getPrice(), productRequest.getQuantityInStock(),
                productRequest.getImgUrl(), category, brand);

    }

}