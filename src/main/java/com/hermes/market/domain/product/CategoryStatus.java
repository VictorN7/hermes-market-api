package com.hermes.market.domain.product;

import com.hermes.market.application.exception.BusinessException;

public enum CategoryStatus {
	
	ACTIVE(1),
	INACTIVE(2);
	
	private int code;
	
	CategoryStatus(int code) {
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
		throw new BusinessException("Invalid CategoryStatus code " + code);
	}
}
