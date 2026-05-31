package com.hermes.market.application.mapper;

import com.hermes.market.application.dto.request.ProductRequest;
import com.hermes.market.application.dto.response.ProductResponse;
import com.hermes.market.application.dto.response.ProductSummaryResponse;
import com.hermes.market.application.dto.response.PromotionResponse;
import com.hermes.market.domain.product.Brand;
import com.hermes.market.domain.product.Category;
import com.hermes.market.domain.product.Product;
import com.hermes.market.domain.product.PromotionStatus;

import java.math.BigDecimal;
import java.util.List;

public class ProductMapper {

    private ProductMapper() {
    }

    public static ProductResponse toAdminResponse(Product product){
        return buildResponse(product, product.getPromotions().stream().map(PromotionMapper::toResponse).toList());
    }

    public static ProductSummaryResponse toSummary(Product product) {
        return new ProductSummaryResponse(product.getId(), product.getName(), product.getPrice(), calculatePromotionPrice(product),
                product.getImgUrl(), product.getStatus().name(), product.getBrand().getName(),
                product.getPromotions().stream().filter(x -> x.getStatus() == PromotionStatus.ACTIVE)
                        .map(PromotionMapper::toSummary).toList());
    }

    public static ProductResponse toResponse(Product product) {
        return buildResponse(product, product.getPromotions().stream()
                .filter(x -> x.getStatus() == PromotionStatus.ACTIVE)
                .map(PromotionMapper::toResponse)
                .toList());
    }

    public static Product toCreate(ProductRequest productRequest, Category category, Brand brand) {

        return new Product(productRequest.getName().trim().replaceAll("\\s+", " "),
                productRequest.getDescription().trim().replaceAll("\\s+", " "),
                productRequest.getPrice(), productRequest.getQuantityInStock(),
                productRequest.getImgUrl().trim().replaceAll("\\s+", " "), category, brand);

    }

    private static ProductResponse buildResponse(Product product, List<PromotionResponse> promotions) {
        return new ProductResponse(product.getId(), product.getName(),product.getDescription(),product.getPrice(),
                calculatePromotionPrice(product),product.getQuantityInStock(),product.getImgUrl(),product.getStatus().name(),
                product.getCreatedAt(),product.getCategory().getName(), product.getBrand().getName(),
                promotions
        );
    }

    private static BigDecimal calculatePromotionPrice(Product product){

        return product.getPromotions().stream()
                .filter(x -> x.getStatus() == PromotionStatus.ACTIVE)
                .findFirst()
                .map(x -> product.getPrice()
                        .subtract(product.getPrice()
                                .multiply(x.getDiscountPercentage())
                                .divide(BigDecimal.valueOf(100)))).orElse(null);

    }
}