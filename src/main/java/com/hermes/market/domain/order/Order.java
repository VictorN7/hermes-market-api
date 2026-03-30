package com.hermes.market.domain.order;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.hermes.market.application.exception.BusinessException;
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
    private final List<OrderItem> orderItems = new ArrayList<>();

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

    protected Order() {
    }

    public Order(User user, PaymentMethod payment, DeliveryMethod delivery, Address address) {
        setUser(user);
        setStatus(OrderStatus.CREATED);
        setTotalPrice(BigDecimal.ZERO);
        createdAt = Instant.now();
        updatedAt = Instant.now();
        setPayment(payment);
        setDelivery(delivery);
        setAddress(address);
    }

    private void setUser(User user) {

        if (user == null) {
            throw new BusinessException("User cannot be null");
        }
        this.user = user;
    }

    private void setStatus(OrderStatus status) {
        if (status == null) {
            throw new BusinessException("Order status cannot be null");
        }
        this.status = status.getCode();
        this.updatedAt = Instant.now();
    }

    private void setTotalPrice(BigDecimal totalPrice) {

        if (totalPrice == null) {
            throw new BusinessException("Total price cannot be null");
        }
        if (totalPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("Total price cannot be negative");
        }

        this.totalPrice = totalPrice;
    }

    private void setPayment(PaymentMethod payment) {
        if (payment == null) {
            throw new BusinessException("Payment method cannot be null");
        }
        this.payment = payment.getCode();
    }

    private void setDelivery(DeliveryMethod delivery) {

        if (!OrderStatus.CREATED.equals(getStatus())){
            throw new BusinessException("Cannot modify items after checkout");
        }

        if (delivery == null) {
            throw new BusinessException("Delivery method cannot be null");
        }
        this.delivery = delivery.getCode();
    }

    private void setAddress(Address address) {

        if (address == null) {
            throw new BusinessException("Address cannot be null");
        }
        this.address = address;
    }

    private BigDecimal calculateTotalPrice() {
        return orderItems.stream().map(OrderItem::getTotalPrice).reduce(BigDecimal.ZERO,
                BigDecimal::add).setScale(2, RoundingMode.HALF_EVEN);
    }

    public void updateItemQuantity(Long itemId, Integer quantity){

        if (!OrderStatus.CREATED.equals(getStatus())){
            throw new BusinessException("Cannot modify items after checkout");
        }

        OrderItem orderItem = orderItems.stream()
                .filter(x -> x.getId()
                        .equals(itemId))
                .findFirst()
                .orElseThrow(() -> new BusinessException("Item not found"));

        Product product = orderItem.getProduct();

        if (product.getQuantityInStock() < quantity){
            throw new BusinessException("Insufficient stock");
        }

        orderItem.updateQuantity(quantity);
        setTotalPrice(calculateTotalPrice());

    }

    public void addItem(Product product, Integer quantity) {

        if(!OrderStatus.CREATED.equals(getStatus())){
            throw new BusinessException("Cannot add items after checkout");
        }

        if (product == null) {
            throw new BusinessException("Product cannot be null");
        }

        if (quantity == null || quantity <= 0) {
            throw new BusinessException("Quantity must be greater than zero");
        }

        if (product.getQuantityInStock() == null|| product.getQuantityInStock() < quantity) {
            throw new BusinessException("Insufficient Stock or null");
        }

        BigDecimal price = product.getEffectivePrice();

        OrderItem item = new OrderItem(product, quantity, price);
        item.setOrder(this);
        updatedAt = Instant.now();
        orderItems.add(item);
        setTotalPrice(calculateTotalPrice());
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public DeliveryMethod getDelivery() {
        return DeliveryMethod.valueOf(delivery);
    }

    public PaymentMethod getPayment() {
        return PaymentMethod.valueOf(payment);
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Address getAddress() {
        return address;
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