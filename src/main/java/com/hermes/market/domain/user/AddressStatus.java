package com.hermes.market.domain.user;

import com.hermes.market.application.exception.BusinessException;

public enum AddressStatus {

    ACTIVE(1),
    INACTIVE(2);

    private final int code;

    AddressStatus(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static AddressStatus valueOf(int code){

        for (AddressStatus status: AddressStatus.values()){
            if(status.getCode() == code){
                return status;
            }
        }
        throw new BusinessException("Invalid AddressStatus code "+ code);
    }
}
