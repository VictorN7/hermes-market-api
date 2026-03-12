package com.hermes.market.application.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class UserPasswordRequest {

    @NotBlank(message = "Current password is required")
    @Size(max = 15)
    private String currentPassword;

    @NotBlank(message = "New password is required")
    @Size(max = 15)
    private String newPassword;

    @NotBlank(message = "Confirm password is required")
    @Size(max = 15)
    private String confirmPassword;

}
