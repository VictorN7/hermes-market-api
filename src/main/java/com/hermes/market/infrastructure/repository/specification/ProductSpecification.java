package com.hermes.market.infrastructure.repository.specification;

import com.hermes.market.domain.product.Product;
import org.springframework.beans.factory.BeanRegistry;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {


    public static Specification<Product> categoryEqual(Long categoryId){
        return (root, query, cb) ->
                categoryId == null ? null:
                cb.equal(root.get("category").get("id"), categoryId);
    }

    public static Specification<Product> brandEqual(Long brandId){
        return (root, query, cb) ->
                brandId == null ? null:
                cb.equal(root.get("brand").get("id"), brandId);
    }

    public static Specification<Product> nameProductLike(String name){
        return (root, query, cb) ->
                (name == null || name.isBlank()) ? null :
                cb.equal(cb.lower(root.get("name")),"%" + name.toLowerCase()+"%");
    }

}
