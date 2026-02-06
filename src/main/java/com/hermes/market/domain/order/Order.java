package com.hermes.market.domain.order;

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
	@JoinColumn(name = "users_id", nullable = false)
	private User user;
	
	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OrderItem> orderItens;
	
	@Column(nullable = false)
	private Integer status;
	
	@Column(nullable = false)
	private Double totalPrice;

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
		this.orderItens = new ArrayList<>();
		setStatus(OrderStatus.CREATED);
		this.totalPrice = 0.0;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
		setPayment(payment);
		setDelivery(delivery);
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItens() {
		return orderItens;
	}

	public OrderStatus getStatus() {
		return OrderStatus.valueOf(status);
	}

	public void setStatus(OrderStatus status) {
		if (status != null) {
			this.status = status.getCode();
		}
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
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

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public Double calculateTotalPrice() {
		return orderItens.stream().mapToDouble(OrderItem::getTotalPrice).sum();
	}

	public void addItem(Product product, Integer quantity) {
		
		OrderItem item = new OrderItem(product, quantity);
		item.setOrder(this);
		item.setPrice(product.getPrice());
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