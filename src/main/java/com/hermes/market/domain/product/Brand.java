package com.hermes.market.domain.product;


import com.hermes.market.application.exception.BusinessException;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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
        products.add(product);
        product.setBrand(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Brand)) return false;
        Brand other = (Brand) o;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '}';
    }
}
