package com.hermes.market.application.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserUpdateRequest {

    @NotBlank(message = "Name is required")
    @Size(max = 50)
    private String name;

    @NotBlank(message = "Email is required")
    @Size(max = 100)
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Birth date is required")
    private LocalDate birthDate;

    public String getName() {
        return name != null ? name.trim(): null;
    }

    public String getEmail() {
        return email != null ? email.trim(): null;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
