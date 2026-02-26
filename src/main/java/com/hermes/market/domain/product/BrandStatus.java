package com.hermes.market.domain.product;

public enum BrandStatus {

	ACTIVE(1),
	INACTIVE(2);

	private int code;

	private BrandStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
	public static BrandStatus valueOf(int code) {
		
		for (BrandStatus brandStatus : BrandStatus.values()) {
			if (brandStatus.getCode() == code) {
				return brandStatus;
			}
		}
		throw new IllegalArgumentException("Invalid BrandStatus code");
	}
}
