package com.hermes.market.application.dto.filter;

public class ProductFilter {

    private Long categoryId;
    private Long brandId;
    private String name;
    private Boolean onSale;

    public ProductFilter(){
    }

    public ProductFilter(Long categoryId, Long brandId, String name, Boolean onSale) {
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.name = name;
        this.onSale = onSale;
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

    public Boolean getOnSale() {
        return onSale;
    }

    public void setOnSale(Boolean onSale) {
        this.onSale = onSale;
    }
}
