package com.hermes.market.domain.product;

import com.hermes.market.application.exception.BusinessException;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_promotions")
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

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal discountPercentage;

    @Column
    private Integer minQuantity;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tb_product_promotion",
            joinColumns = @JoinColumn(name = "promotion_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();

    protected Promotion(){
    }

    public Promotion(String name, Instant startDate, Instant endDate,
                     PromotionType type, BigDecimal discountPercentage, Integer minQuantity) {
        setName(name);
        setPeriod(startDate,endDate);
        setStatus(PromotionStatus.ACTIVE);
        setType(type);
        setDiscountPercentage(discountPercentage);
        setMinQuantity(minQuantity);
        validateTypePromotion();
    }

    private void setStatus(PromotionStatus status) {
        if (status == null) {
            throw new BusinessException("Status can not be null");
        }
        this.status = status.getCode();
    }

    private void setType(PromotionType type) {

        if (type == null){
            throw new BusinessException("Type can not be null");
        }
        this.type = type.getCode();
    }

    private void setMinQuantity(Integer minQuantity) {

        if (minQuantity != null && minQuantity <= 0){
            throw new BusinessException("Minimum quantity cannot be less than or equal to 0");
        }
        this.minQuantity = minQuantity;
    }

    private void setPeriod(Instant startDate, Instant endDate){

        if (startDate == null || endDate == null){
            throw new BusinessException("Promotion dates cannot be null");
        }
        if (endDate.isBefore(startDate)){
            throw new BusinessException("End date must be after begin date");
        }
        if (startDate.isBefore(Instant.now())){
            throw new BusinessException("Begin date cannot be in the past");
        }

        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void setDiscountPercentage(BigDecimal discountPercentage){

        if (discountPercentage == null){
            throw new BusinessException("Discount percentage cannot be null");
        }
        if (discountPercentage.compareTo(BigDecimal.ONE) < 0 ||
                discountPercentage.compareTo(BigDecimal.valueOf(100)) > 0){
            throw new BusinessException("Discount percentage must be between 1 and 100");
        }
        this.discountPercentage = discountPercentage.setScale(2, RoundingMode.HALF_UP);
    }

    private void setName(String name) {

        if (name == null || name.isBlank()){
            throw new BusinessException("Name cannot be null or empty");
        }
        this.name = name;
    }

    private void validateTypePromotion(){

        PromotionType promotionType = getType();

        if (promotionType.equals(PromotionType.DIRECT_PRICE)){

            if (minQuantity != null){
                throw new BusinessException("Minimum quantity are not allowed for DIRECT_PRICE promotions");
            }
        }
        if (promotionType.equals(PromotionType.QUANTITY_DISCOUNT)){
            if (minQuantity == null){
                throw new BusinessException("Minimum quantity is required for QUANTITY_DISCOUNT promotions");
            }
        }
    }

    public void addProduct(Product product){

        if (product == null ){
            throw new BusinessException("Product cannot be null");
        }
        if (products.contains(product)) return;

        products.add(product);
    }

    public Long getId() {
        return id;
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

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public Integer getMinQuantity() {
        return minQuantity;
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
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                ", type=" + type +
                ", discountPercentage=" + discountPercentage +
                ", minQuantity=" + minQuantity +
                '}';
    }
}
