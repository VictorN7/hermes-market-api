package com.hermes.market.domain.product;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_promotion")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Instant startDate;

    @Column(nullable = false)
    private Instant endDate;

    @Column(nullable = false)
    private Integer status;

    @Column(nullable = false)
    private Integer type;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal promotionalPrice;

    @Column
    private Integer minQuantity;

    @Column(precision = 10, scale = 2)
    private BigDecimal discountedUnitPrice;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tb_product_promotion",
            joinColumns = @JoinColumn(name = "promotion_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();

    public Promotion(){

    }

    public Promotion(String name, Instant startDate, Instant endDate,
                     PromotionType type, BigDecimal promotionalPrice, Integer minQuantity, BigDecimal discountedUnitPrice) {
        this.name = name;
        setPeriod(startDate,endDate);
        setStatus(PromotionStatus.ACTIVE);
        setType(type);
        setPromotionalPrice(promotionalPrice);
        this.minQuantity = minQuantity;
        this.discountedUnitPrice = discountedUnitPrice;
        validateTypePromotion();
    }

    private void validateTypePromotion(){
        if (PromotionType.valueOf(type).equals(PromotionType.DIRECT_PRICE)){

            if (minQuantity != null || discountedUnitPrice != null){
                throw new IllegalArgumentException("minQuantity and discountedUnitPrice are not allowed for DIRECT_PRICE promotions");
            }
        }
        if (PromotionType.valueOf(type).equals(PromotionType.QUANTITY_DISCOUNT)){
            if (discountedUnitPrice == null || minQuantity == null){
                throw new IllegalArgumentException("minQuantity is required for QUANTITY_DISCOUNT promotions");
            }
            if (discountedUnitPrice.compareTo(BigDecimal.ZERO) <= 0 || minQuantity <= 0){
                throw new IllegalArgumentException("discountedUnitPrice or minQuantity must be greater than zero");
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(PromotionStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status can not be null");
        }
        this.status = status.getCode();
    }

    public void setType(PromotionType type) {

        if (type == null){
            throw new IllegalArgumentException("Type can not be null");
        }
        this.type = type.getCode();
    }

    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }

    public void setDiscountedUnitPrice(BigDecimal discountedUnitPrice) {
        this.discountedUnitPrice = discountedUnitPrice;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getName() {
        return name;
    }

    public PromotionStatus getStatus() {
        return PromotionStatus.valueOf(status);
    }

    public PromotionType getType() {
        return PromotionType.valueOf(type);
    }

    public BigDecimal getPromotionalPrice() {
        return promotionalPrice;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public BigDecimal getDiscountedUnitPrice() {
        return discountedUnitPrice;
    }

    public void setPeriod(Instant startDate, Instant endDate){

        if (startDate == null || endDate == null){
            throw new IllegalArgumentException("Promotion dates cannot be null");
        }
        if (endDate.isBefore(startDate)){
            throw new IllegalArgumentException("End date must be after begin date");
        }
        if (startDate.isBefore(Instant.now())){
            throw new IllegalArgumentException("Begin date cannot be in the past");
        }

        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setPromotionalPrice(BigDecimal promotionalPrice){

        if (promotionalPrice == null){
            throw new IllegalArgumentException();
        }
        if (promotionalPrice.compareTo(BigDecimal.ZERO) <= 0 ){
            throw new IllegalArgumentException("Price must be greater than zero");
        }
        this.promotionalPrice = promotionalPrice;
    }

    public void addProduct(Product product){

        if (promotionalPrice.compareTo(product.getPrice()) >= 0){
            throw new IllegalArgumentException("promotionalPrice cannot be less than or equal to the product's value");
        }
        products.add(product);
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Promotion promotion = (Promotion) o;
        return Objects.equals(id, promotion.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", begin=" + startDate +
                ", end=" + endDate +
                ", status=" + status +
                ", type=" + type +
                ", promotionalPrice=" + promotionalPrice +
                ", minQuantity=" + minQuantity +
                ", discountedUnitPrice=" + discountedUnitPrice +
                '}';
    }
}
