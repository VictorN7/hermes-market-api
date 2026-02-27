package com.hermes.market.domain.order;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.hermes.market.domain.product.Product;
import com.hermes.market.domain.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OrderItem> orderItens = new ArrayList<>();
	
	@Column(nullable = false)
	private Integer status;
	
	@Column(nullable = false, precision = 15, scale = 2)
	private BigDecimal totalPrice;

	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	@Column(nullable = false)
	private LocalDateTime updatedAt;
	
	@Column(nullable = false)
	private Integer payment;
	
	@Column(nullable = false)
	private Integer delivery;
	
	public Order(){
	}

	public Order(User user,PaymentMethod payment, DeliveryMethod delivery) {
		this.user = user;
		orderItens = new ArrayList<>();
		setStatus(OrderStatus.CREATED);
		totalPrice =  BigDecimal.ZERO;
		createdAt = LocalDateTime.now();
		updatedAt = LocalDateTime.now();
		setPayment(payment);
		setDelivery(delivery);
	}
	
	public User getUser() {
		return user;
	}

	public List<OrderItem> getOrderItens() {
		return List.copyOf(orderItens);
	}

	public OrderStatus getStatus() {
		return OrderStatus.valueOf(status);
	}

	public void setStatus(OrderStatus status) {
		if (status != null) {
			this.status = status.getCode();
		}
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public PaymentMethod getPayment() {
		return PaymentMethod.valueOf(payment);
	}

	public void setPayment(PaymentMethod payment) {
		if (payment != null) {
			this.payment = payment.getCode();
		}
	}

	public DeliveryMethod getDelivery() {
		return DeliveryMethod.valueOf(delivery);
	}

	public void setDelivery(DeliveryMethod delivery) {
		if (delivery != null) {
			this.delivery = delivery.getCode();
		}
	}
	
	public BigDecimal calculateTotalPrice() {
		return orderItens.stream().map(OrderItem::getTotalPrice).reduce(BigDecimal.ZERO,
				BigDecimal::add).setScale(2, RoundingMode.HALF_EVEN);
	}

	public void addItem(Product product, Integer quantity) {
		
		OrderItem item = new OrderItem(product, quantity);
		item.setOrder(this);
		this.updatedAt = LocalDateTime.now();
		this.orderItens.add(item);
		this.totalPrice = calculateTotalPrice();
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
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", user=" + user + ", status=" + status + ", totalPrice=" + totalPrice
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", payment=" + payment + ", delivery="
				+ delivery + "]";
	}

}