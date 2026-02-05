package com.hermes.market.domain.product;

public enum CategoryStatus {
	
	ACTIVE(1),
	INACTIVE(2);
	
	private int code;
	
	private CategoryStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
	public static CategoryStatus valueOf(int code) {
		
		for (CategoryStatus categoryStatus : CategoryStatus.values()) {
			if (categoryStatus.getCode() == code) {
				return categoryStatus;
			}
		}
		throw new IllegalArgumentException("Invalid CategoryStatus code");
	}
}
