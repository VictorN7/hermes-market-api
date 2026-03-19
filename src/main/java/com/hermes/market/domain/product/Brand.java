package com.hermes.market.domain.product;


import com.hermes.market.application.exception.BusinessException;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_brands")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer status;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

    protected Brand() {
    }

    public Brand(String name) {
        setName(name);
        setStatus(BrandStatus.ACTIVE);
        createdAt = Instant.now();
    }

    private void setName(String name) {

        if (name == null || name.isBlank()) {
            throw new BusinessException("Brand name cannot be null or empty");
        }
        this.name = name;
    }

    public void updateName(String name) {

        if (BrandStatus.INACTIVE.equals(getStatus())) {
            throw new BusinessException("Inactive brand cannot be updated");
        }

        setName(name);
    }

    public void activate() {

        if (BrandStatus.ACTIVE.equals(getStatus())) {
            throw new BusinessException("Brand is already active");
        }

        setStatus(BrandStatus.ACTIVE);
    }

    public void deactivate() {

        if (BrandStatus.INACTIVE.equals(getStatus())) {
            throw new BusinessException("Brand is already inactive");
        }

        setStatus(BrandStatus.INACTIVE);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public BrandStatus getStatus() {
        return status == null ? null : BrandStatus.valueOf(status);
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    private void setStatus(BrandStatus status) {
        if (status == null) {
            throw new BusinessException("Brand status cannot be null");
        }
        this.status = status.getCode();
    }

    public void addProduct(Product product) {


        if (product == null){
            throw new BusinessException("Product cannot be null");
        }
        if (products.contains(product)) return;

        products.add(product);
        product.assignBrand(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Brand brand = (Brand) o;
        return Objects.equals(id, brand.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '}';
    }
}
