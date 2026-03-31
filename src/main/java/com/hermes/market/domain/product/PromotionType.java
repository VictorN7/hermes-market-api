package com.hermes.market.domain.product;

import com.hermes.market.application.exception.BusinessException;

public enum PromotionType {

    DIRECT_PRICE(1),
    QUANTITY_DISCOUNT(2);


    private final int code;


    PromotionType(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static PromotionType valueOf(int code){

        for (PromotionType type : PromotionType.values()){
            if (type.getCode() == code){
                return type;
            }
        }
        throw new BusinessException("Invalid PromotionType code " + code);
    }
}
