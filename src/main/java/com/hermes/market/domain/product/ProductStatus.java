package com.hermes.market.domain.product;

import com.hermes.market.application.exception.BusinessException;

public enum ProductStatus {

	ACTIVE(1),
	INACTIVE(2),
	NO_STOCK(3);
	
	private int code;
	
	ProductStatus(int code) {
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
		throw new BusinessException("Invalid ProductStatus code " + code);
	}
}
