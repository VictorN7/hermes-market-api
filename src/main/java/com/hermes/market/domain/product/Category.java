package com.hermes.market.domain.product;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.hermes.market.application.exception.BusinessException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Integer status;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

    protected Category() {
    }

    public Category(String name) {
        this.name = name;
        setStatus(CategoryStatus.ACTIVE);
        this.createdAt = Instant.now();
    }

    public void updateName(String name){
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public void deactivateCategory(){

        if (getStatus() == CategoryStatus.INACTIVE){
            throw new BusinessException("Category is already inactive");
        }

        setStatus(CategoryStatus.INACTIVE);
    }

    private void setStatus(CategoryStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("CategoryStatus cannot be null");
        }
        this.status = status.getCode();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CategoryStatus getStatus() {
        return CategoryStatus.valueOf(status);
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
        product.setCategory(this);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category other = (Category) o;
        return id != null && id.equals(other.id);
    }

    @Override
    public String toString() {
        return "Category [id=" + id + ", name=" + name + ", status=" + status + ", createdAt=" + createdAt
                + "]";
    }
}