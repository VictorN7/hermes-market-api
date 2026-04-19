package com.hermes.market.infrastructure.repository.specification;

import com.hermes.market.domain.product.Product;
import com.hermes.market.domain.product.ProductStatus;
import com.hermes.market.domain.product.PromotionStatus;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> categoryEqual(Long categoryId) {
        return (root, query, cb) ->
                categoryId == null ? null :
                        cb.equal(root.get("category").get("id"), categoryId);
    }

    public static Specification<Product> brandEqual(Long brandId) {
        return (root, query, cb) ->
                brandId == null ? null :
                        cb.equal(root.get("brand").get("id"), brandId);
    }

    public static Specification<Product> nameProductLike(String name) {
        return (root, query, cb) ->
                (name == null || name.isBlank()) ? null :
                        cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Product> statusEqual(Integer status) {
        return (root, query, cb) ->
                status == null ? null:
                    cb.equal(root.get("status"), status);
    }

    public static Specification<Product> containsPromotion(Boolean bol) {
        return (root, query, cb) -> {
            if (bol == null || !bol) return null;

            var joinVar = root.join("promotions");
            return cb.equal(joinVar.get("status"), PromotionStatus.ACTIVE.getCode());

        };
    }
}
