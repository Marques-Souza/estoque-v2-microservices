package com.estoque.cadastro_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClientRequestDto(

        @NotBlank(message = "Name is required")
        @Size(max = 150, message = "Name must have a maximum of 150 characters")
        String name,

        @NotBlank(message = "Email is required")
        @Size(max = 150, message = "Email must have a maximum of 150 characters")
        @Pattern(regexp = "^\\S+@\\S+\\.\\S+$", message = "Email must be valid")
        String email,

        @NotBlank(message = "Phone is required")
        @Size(max = 20, message = "Phone must have a maximum of 20 characters")
        String phone,

        @NotBlank(message = "CNPJ is required")
        @Size(max = 14, message = "CNPJ must have a maximum of 14 characters")
        @Pattern(regexp = "^\\d{14}$", message = "CNPJ must have exactly 14 digits")
        String cnpj

) {
}
