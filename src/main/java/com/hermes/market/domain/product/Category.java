package com.hermes.market.domain.product;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@Column(nullable = false )
	private Integer status;

	@Column(nullable = false)
	private Instant createdAt;
	
	@JsonIgnore
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private List<Product> products = new ArrayList<>();
	
	public Category() {
	}

	public Category(String name) {
		this.name = name;
		setStatus(CategoryStatus.ACTIVE);;
		this.createdAt = Instant.now();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStatus(CategoryStatus status) {
		if (status != null) {
			this.status = status.getCode();
		}
	}

	public void setProducts(List<Product> products) {
		this.products = products;
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
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", status=" + status + ", createdAt=" + createdAt
				+"]";
	}
}