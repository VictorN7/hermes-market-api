package com.hermes.market.domain.product;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        setName(name);
        setStatus(CategoryStatus.ACTIVE);
        this.createdAt = Instant.now();
    }

    private void setName(String name) {

        if (name == null || name.isBlank()){
            throw new BusinessException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public void updateName(String name){

        if (CategoryStatus.INACTIVE.equals(getStatus())) {
            throw new BusinessException("Inactive category cannot be updated");
        }
        setName(name);
    }

    public void deactivate(){

        if (CategoryStatus.INACTIVE.equals(getStatus())){
            throw new BusinessException("Category is already inactive");
        }

        setStatus(CategoryStatus.INACTIVE);
    }

    public void activate(){

        if (CategoryStatus.ACTIVE.equals(getStatus())){
            throw new BusinessException("Category is already active");
        }
        setStatus(CategoryStatus.ACTIVE);
    }

    private void setStatus(CategoryStatus status) {
        if (status == null) {
            throw new BusinessException("Category status cannot be null");
        }
        this.status = status.getCode();
    }

    public void addProduct(Product product) {

        if (product == null){
            throw new BusinessException("Product cannot be null");
        }
        if (products.contains(product)) return;

        products.add(product);
        product.assignCategory(this);
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Category [id=" + id + ", name=" + name + ", status=" + status + ", createdAt=" + createdAt
                + "]";
    }
}