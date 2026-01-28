package com.hermes.market.domain.product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@Column(nullable = false )
	@Enumerated(EnumType.STRING)
	private CategoryStatus status;

	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private List<Product> products;
	
	public Category() {
		
	}

	public Category(String name, List<Product> products) {
		this.name = name;
		this.status = CategoryStatus.ACTIVE;
		this.createdAt = LocalDateTime.now();
		this.products = products;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStatus(CategoryStatus status) {
		this.status = status;
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
		return status;
	}

	public LocalDateTime getCreatedAt() {
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