package com.hermes.market.domain.order;

public enum DeliveryMethod {

	PICKUP(1),
	HOME_DELIVERY(2);
	
	private int code;
	
	private DeliveryMethod(int code) {
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
		throw new IllegalArgumentException("Invalid DeliveryMethod code");
	}
}