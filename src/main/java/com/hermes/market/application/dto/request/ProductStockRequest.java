package com.hermes.market.application.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ProductStockRequest {

    @NotNull(message = "QuantityInStock is required")
    @Min(value = 0, message = "Quantity in stock must be greater than or equal to zero")
    private Integer quantity;

}
