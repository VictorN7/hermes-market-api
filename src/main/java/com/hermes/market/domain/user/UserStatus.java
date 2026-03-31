package com.hermes.market.domain.user;


import com.hermes.market.application.exception.BusinessException;

public enum UserStatus {

	ACTIVE(1),
	INACTIVE(2),
	BLOCKED(3);
	
	private int code;
	
	UserStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
	public static UserStatus valueOf(int code) {
		
		for (UserStatus status : UserStatus.values()) {
			if (status.getCode() == code) {
				return status;
			}
		}
		throw new BusinessException("Invalid UserStatus code "+ code);
	}
}