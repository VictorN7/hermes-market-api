package com.hermes.market.domain.order;

import com.hermes.market.application.exception.BusinessException;

public enum DeliveryMethod {

	PICKUP(1),
	HOME_DELIVERY(2);
	
	private int code;
	
	DeliveryMethod(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
	public static DeliveryMethod valueOf(int code) {
		
		for (DeliveryMethod deliveryMethod : DeliveryMethod.values()) {
			if (deliveryMethod.getCode() == code) {
				return deliveryMethod;
			}
		}
		throw new BusinessException("Invalid DeliveryMethod code "+ code);
	}
}