package com.expleo.assignment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PasswordChangeDTO {

    @NotBlank(message = "Old password is mandatory")
    private String oldPassword;

    @NotBlank(message = "New password is mandatory")
    @Size(min = 8, message = "New password must be at least 8 characters long")
    private String newPassword;
}
