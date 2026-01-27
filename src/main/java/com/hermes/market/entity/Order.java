package com.hermes.market.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.hermes.market.enums.DeliveryMethod;
import com.hermes.market.enums.OrderStatus;
import com.hermes.market.enums.PaymentMethod;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
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
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@Column(nullable = false)
	private Double totalPrice;

	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	@Column(nullable = false)
	private LocalDateTime updatedAt;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private PaymentMethod payment;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private DeliveryMethod delivery;
	
	public Order(){
	}

	public Order(User user, List<OrderItem> orderItens, PaymentMethod payment, DeliveryMethod delivery) {
		this.user = user;
		this.orderItens = orderItens;
		this.status = OrderStatus.CREATED;
		this.totalPrice = calculateTotalPrice();
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
		this.payment = payment;
		this.delivery = delivery;
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

	public void setOrderItens(List<OrderItem> orderItens) {
		this.orderItens = orderItens;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
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
		return payment;
	}

	public void setPayment(PaymentMethod payment) {
		this.payment = payment;
	}

	public DeliveryMethod getDelivery() {
		return delivery;
	}

	public void setDelivery(DeliveryMethod delivery) {
		this.delivery = delivery;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public Double calculateTotalPrice() {
		return orderItens.stream().mapToDouble(OrderItem::getTotalPrice).sum();
	}

	public void addItem(OrderItem item) {
		item.setOrder(this);
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