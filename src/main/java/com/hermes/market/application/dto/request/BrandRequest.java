package com.hermes.market.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class BrandRequest {

    @NotBlank(message = "Name is required")
    @Size(max = 25)
    private String name;

}
