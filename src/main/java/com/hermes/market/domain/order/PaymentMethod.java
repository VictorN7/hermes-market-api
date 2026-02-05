package com.hermes.market.domain.order;


public enum PaymentMethod {
	
	CASH(1), 
	PIX(2),
	BOLETO(3),
	CREDIT_CARD(4),
	DEBIT_CARD(5);
	
	private int code;
	
	private PaymentMethod(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
	public static PaymentMethod valueOf(int code) {
		
		for (PaymentMethod paymentMethod : PaymentMethod.values()) {
			if (paymentMethod.getCode() == code) {
				return paymentMethod;
			}
		}
		throw new IllegalArgumentException("Invalid PaymentMethod code");
	}
}