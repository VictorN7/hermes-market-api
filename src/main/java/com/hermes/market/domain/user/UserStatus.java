package com.hermes.market.domain.user;


public enum UserStatus {

	ACTIVE(1),
	INACTIVE(2),
	BLOCKED(3);
	
	private int code;
	
	private UserStatus(int code) {
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
		throw new IllegalArgumentException("Invalid UserStatus code");
	}
}