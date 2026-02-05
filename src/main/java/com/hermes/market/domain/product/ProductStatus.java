package com.hermes.market.domain.product;

public enum ProductStatus {

	ACTIVE(1),
	INACTIVE(2),
	NO_STOCK(3);
	
	private int code;
	
	private ProductStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
	public static ProductStatus valueOf(int code) {
		
		for (ProductStatus productStatus : ProductStatus.values()) {
			if (productStatus.getCode() == code) {
				return productStatus;
			}
		}
		throw new IllegalArgumentException("Invalid ProductStatus code");
	}
}
