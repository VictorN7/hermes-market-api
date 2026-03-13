package com.hermes.market.application.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrderRequest {

    @NotNull(message = "User is required")
    private Long userId;

    @NotNull(message = "Payment is required")
    private Integer payment;

    @NotNull(message = "Delivery is required")
    private Integer delivery;

    @NotNull(message = "Address is required")
    private Long addressId;

}
