package com.hermes.market.domain.product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.hermes.market.application.exception.BusinessException;
import jakarta.persistence.*;

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
	
	@Column(nullable = false, updatable = false)
	private Instant createdAt;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@ManyToOne
	@JoinColumn(name = "brand_id", nullable = false)
	private Brand brand;

	@ManyToMany(mappedBy = "products")
	private List<Promotion> promotions = new ArrayList<>();

	protected Product() {
	}

	public Product(String name, String description, BigDecimal price, Integer quantityInStock, String imgUrl, Category category, Brand brand) {
		setName(name);
		setDescription(description);
		setPrice(price);
		setQuantityInStock(quantityInStock);
		setStatus(ProductStatus.ACTIVE);
		this.createdAt = Instant.now();
		setImgUrl(imgUrl);
		assignCategory(category);
		assignBrand(brand);
	}

	public void adjustStock(Integer quantityInStock){

		if (quantityInStock == null) {
			throw new BusinessException("Quantity cannot be null");
		}
		if (quantityInStock < 0) {
			throw new BusinessException("Stock cannot be negative");
		}
		this.quantityInStock = quantityInStock;
	}

	public void updateProduct(String name, String description, BigDecimal price, String imgUrl, Category category, Brand brand){

		setName(name);
		setDescription(description);
		setPrice(price);
		setImgUrl(imgUrl);
		assignCategory(category);
		assignBrand(brand);
	}

	private void setName(String name) {

		if (name == null){
			throw new BusinessException("Name cannot be null");
		}
		this.name = name;
	}

	private void setDescription(String description) {

		if (description == null){
			throw new BusinessException("Description cannot be null");
		}
		this.description = description;
	}

	private void setImgUrl(String imgUrl) {

		if (imgUrl == null){
			throw new BusinessException("Image Url cannot be null");
		}
		this.imgUrl = imgUrl;
	}

	public void assignBrand(Brand brand){
		if (brand == null){
			throw new BusinessException("Brand cannot be null");
		}
		this.brand = brand;
	}

	public void assignCategory(Category category){

		if (category == null) {
			throw new BusinessException("Category cannot be null");
		}
		this.category = category;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	private void setPrice(BigDecimal price) {
		if (price == null || price.compareTo(BigDecimal.ZERO) < 0 ){
			throw new BusinessException("Price cannot be null or negative");
		}
		this.price = price.setScale(2, RoundingMode.HALF_EVEN);
	}

	private void setQuantityInStock(Integer quantityInStock) {
		if (quantityInStock == null || quantityInStock < 0){
			throw new BusinessException("Quantity In Stock cannot be null or negative");
		}
		this.quantityInStock = quantityInStock;
	}

	private void setStatus(ProductStatus status) {
		if (status == null) {
			throw new BusinessException("Status cannot be null");
		}
		this.status = status.getCode();
	}

	public List<Promotion> getPromotions() { return promotions; }

	public Brand getBrand() {
		return brand;
	}

	public Category getCategory() {
		return category;
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
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Product product = (Product) o;
		return Objects.equals(id, product.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
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
				'}';
	}
}