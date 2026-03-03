package com.hermes.market.application.dto.filter;

public class ProductFilter {

    private Long categoryId;
    private Long brandId;
    private String name;

    public ProductFilter(){
    }

    public ProductFilter(Long categoryId, Long brandId, String name) {
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
