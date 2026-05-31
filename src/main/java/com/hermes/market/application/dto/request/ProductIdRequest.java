package com.hermes.market.application.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class ProductIdRequest {

    @NotNull(message = "product id is required")
    @Positive(message = "product id must be greater than 0")
    private Long productId;

}
