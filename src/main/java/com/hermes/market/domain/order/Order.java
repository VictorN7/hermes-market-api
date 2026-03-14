package com.hermes.market.domain.order;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.hermes.market.domain.product.Product;
import com.hermes.market.domain.user.Address;
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
	private List<OrderItem> orderItems;
	
	@Column(nullable = false)
	private Integer status;
	
	@Column(nullable = false, precision = 15, scale = 2)
	private BigDecimal totalPrice;

	@Column(nullable = false, updatable = false)
	private Instant createdAt;
	
	@Column(nullable = false)
	private Instant updatedAt;
	
	@Column(nullable = false)
	private Integer payment;
	
	@Column(nullable = false)
	private Integer delivery;

	@ManyToOne
	@JoinColumn(name = "address_id", nullable = false)
	private Address address;
	
	protected Order(){
	}

	public Order(User user,PaymentMethod payment, DeliveryMethod delivery, Address address ) {
		this.user = user;
		orderItems = new ArrayList<>();
		setStatus(OrderStatus.CREATED);
		totalPrice =  BigDecimal.ZERO;
		createdAt = Instant.now();
		updatedAt = Instant.now();
		setPayment(payment);
		setDelivery(delivery);
		this.address = address;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public User getUser() {
		return user;
	}

	public List<OrderItem> getOrderItems() {
		return List.copyOf(orderItems);
	}

	public OrderStatus getStatus() {
		return OrderStatus.valueOf(status);
	}

	public void setStatus(OrderStatus status) {
		if (status == null) {
			throw new IllegalArgumentException("OrderStatus cannot be null");
		}
		this.status = status.getCode();
		this.updatedAt = Instant.now();
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}


	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public PaymentMethod getPayment() {
		return PaymentMethod.valueOf(payment);
	}

	public void setPayment(PaymentMethod payment) {
		if (payment == null) {
			throw new IllegalArgumentException("PaymentMethod cannot be null");
		}
		this.payment = payment.getCode();
	}

	public DeliveryMethod getDelivery() {
		return DeliveryMethod.valueOf(delivery);
	}

	public void setDelivery(DeliveryMethod delivery) {
		if (delivery == null) {
			throw new IllegalArgumentException("DeliveryMethod cannot be null");
		}
		this.delivery = delivery.getCode();
	}
	
	public BigDecimal calculateTotalPrice() {
		return orderItems.stream().map(OrderItem::getTotalPrice).reduce(BigDecimal.ZERO,
				BigDecimal::add).setScale(2, RoundingMode.HALF_EVEN);
	}

	public void addItem(Product product, Integer quantity) {

		if (product.getQuantityInStock() < quantity){
			throw new IllegalArgumentException("Insufficient Stock");
		}

		OrderItem item = new OrderItem(product, quantity);
		item.setOrder(this);
		this.updatedAt = Instant.now();
		this.orderItems.add(item);
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