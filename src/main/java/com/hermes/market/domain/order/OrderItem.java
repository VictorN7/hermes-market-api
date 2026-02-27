package com.hermes.market.domain.order;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hermes.market.domain.product.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "tb_order_items")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;
	
	@Column(nullable = false)
	private Integer quantity;
	
	@Column(nullable = false, precision = 15, scale = 2)
	private BigDecimal price = BigDecimal.ZERO;

	public OrderItem() {
	}
	
	public OrderItem(Product product, Integer quantity) {
		this.product = Objects.requireNonNull(product);
		this.quantity = validateQuantity(quantity);
		price = Objects.requireNonNull(product.getPrice());
	}

	public BigDecimal getTotalPrice() {
		return price.multiply(BigDecimal.valueOf(quantity)).setScale(2, RoundingMode.HALF_EVEN);
	}

	public Long getId() {
		return id;
	}

	public Product getProduct() {
		return product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	private Integer validateQuantity(Integer quantity){
		if (quantity == null || quantity <= 0){
			throw new IllegalArgumentException("Quantity can not be null");
		}
		return quantity;
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
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", quantity=" + quantity + ", price=" + price + "]";
	}
	
}