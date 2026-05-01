package com.hermes.market.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CategoryRequest {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 25)
    private String name;

}
