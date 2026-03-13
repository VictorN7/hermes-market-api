package com.hermes.market.application.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class PromotionRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "StartDate is required")
    private Instant startDate;

    @NotNull(message = "EndDate is required")
    private Instant endDate;

    @NotNull(message = "Type is required")
    private Integer type;

    @NotNull(message = "PromotionalPrice is required")
    @DecimalMin(value = "0.01", message = "PromotionalPrice must be greater than zero")
    private BigDecimal promotionalPrice;

    private Integer minQuantity;

}
