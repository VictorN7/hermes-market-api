package com.hermes.market.domain.product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false, precision = 15, scale = 2)
	private BigDecimal price;
	
	@Column(nullable = false)
	private Integer quantityInStock; 
	
	@Column(nullable = false)
	private String imgUrl;
	
	@Column(nullable = false)
	private Integer status; 
	
	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant createdAt;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "brand_id", nullable = false)
	private Brand brand;

	public Product() {
	}

	public Product(String name, String description, BigDecimal price, Integer quantityInStock, String imgUrl, Category category, Brand brand) {
		this.name = name;
		this.description = description;
		setPrice(price);
		setQuantityInStock(quantityInStock);
		setStatus(ProductStatus.ACTIVE);
		this.createdAt = Instant.now();
		this.imgUrl = imgUrl;
		setCategory(category);
		setBrand(brand);
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		if (brand == null){
			throw new IllegalArgumentException("Product must have a brand!");
		}
		this.brand = brand;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		
		if (category == null) {
			throw new IllegalArgumentException("Product must have a category!");
		}
		this.category = category;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(BigDecimal price) {
		if (price == null || price.compareTo(BigDecimal.ZERO) < 0 ){
			throw new IllegalArgumentException("Price can not be null or negative!");
		}
		this.price = price.setScale(2, RoundingMode.HALF_EVEN);
	}

	public void setQuantityInStock(Integer quantityInStock) {
		if (quantityInStock == null || quantityInStock < 0){
			throw new IllegalArgumentException("QuantityInStock can not be null or negative!");
		}
		this.quantityInStock = quantityInStock;
	}

	public void setStatus(ProductStatus status) {
		if (status == null) {
			throw new IllegalArgumentException("Status cannot be null");
		}
		this.status = status.getCode();
	}
	
	public Long getId() {
		return id;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}


	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Integer getQuantityInStock() {
		return quantityInStock;
	}

	public ProductStatus getStatus() {
		return ProductStatus.valueOf(status);
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
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", price=" + price +
				", quantityInStock=" + quantityInStock +
				", imgUrl='" + imgUrl + '\'' +
				", status=" + status +
				", createdAt=" + createdAt +
				", category=" + category +
				", brand=" + brand +
				'}';
	}
}