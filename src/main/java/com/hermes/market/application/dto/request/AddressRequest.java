package com.hermes.market.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class AddressRequest {

    @NotNull
    @Size(max = 10)
    private Integer number;

    @NotBlank(message = "Street is required")
    @Size(max = 50)
    private String street;

    @Size(max = 50)
    private String complement;

    @NotBlank(message = "Neighborhood is required")
    @Size(max = 20)
    private String neighborhood;

    @NotBlank(message = "City is required")
    @Size(max = 20)
    private String city;

    @NotBlank(message = "State is required")
    @Size(max = 20)
    private String state;

    @NotBlank(message = "CEP is required")
    @Size(max = 8)
    private String zipcode;

}
