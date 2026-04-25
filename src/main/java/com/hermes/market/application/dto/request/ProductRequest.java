package com.hermes.market.application.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ProductRequest {

    @NotBlank(message = "Name is required")
    @Size(max = 50)
    private String name;

    @NotBlank(message = "Description is required")
    @Size(max = 100)
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must have greater than zero")
    @DecimalMax(value="1000")
    private BigDecimal price;

    @NotNull(message = "QuantityInStock is required")
    private Integer quantityInStock;

    @NotBlank(message = "Image is required")
    @Size(max = 255)
    private String imgUrl;

    @NotNull(message = "Category is required")
    private Long categoryId;

    @NotNull(message = "Brand is required")
    private Long brandId;

}
