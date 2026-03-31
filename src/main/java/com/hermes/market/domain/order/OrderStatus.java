package com.hermes.market.domain.order;

import com.hermes.market.application.exception.BusinessException;

public enum OrderStatus {

	CREATED(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
	private int code;
	
	OrderStatus(int code) {
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
		throw new BusinessException("Invalid OrderStatus code " + code);
	}
}