package com.hermes.market.application.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class AddressUpdateRequest {

    @NotNull(message = "Number is required")
    @Min(value = 1)
    @Max(value = 100000)
    private Integer number;

    @NotBlank(message = "Street is required")
    @Size(min = 3, max = 100)
    private String street;

    @Size(max = 60)
    private String complement;

    @NotBlank(message = "Neighborhood is required")
    @Size(min = 2, max = 60)
    private String neighborhood;

    @NotBlank(message = "City is required")
    @Size(min = 3,max = 60)
    private String city;

    @NotBlank(message = "State is required")
    @Size(min = 2, max = 2)
    private String state;

    @NotBlank(message = "CEP is required")
    @Pattern(regexp = "\\d{8}", message = "CEP must have 8 digits")
    private String zipcode;

}
