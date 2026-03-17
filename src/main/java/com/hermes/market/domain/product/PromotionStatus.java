package com.hermes.market.domain.product;

public enum PromotionStatus {

    ACTIVE(1),
    INACTIVE(2);

    private final int code;

    PromotionStatus(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static PromotionStatus valueOf(int code){

        for (PromotionStatus status : PromotionStatus.values()){
            if (status.getCode() == code){
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid PromotionStatus code "+code);
    }
}