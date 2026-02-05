package com.hermes.market.domain.order;

public enum OrderStatus {

	CREATED(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
	private int code;
	
	private OrderStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
	public static OrderStatus valueOf(int code) {
		
		for (OrderStatus orderStatus : OrderStatus.values()) {
			if (orderStatus.getCode() == code) {
				return orderStatus;
			}
		}
		throw new IllegalArgumentException("Invalid OrderStatus code");
	}
}