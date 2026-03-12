package com.hermes.market.application.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ProductStockRequest {

    @NotNull(message = "QuantityInStock is required")
    private Integer quantityInStock;

}
