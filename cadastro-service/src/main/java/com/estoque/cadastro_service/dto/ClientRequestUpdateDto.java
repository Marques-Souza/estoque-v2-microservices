package com.estoque.cadastro_service.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClientRequestUpdateDto(

        @Size(max = 150, message = "Name must have a maximum of 150 characters")
        String name,

        @Size(max = 150, message = "Email must have a maximum of 150 characters")
        @Pattern(regexp = "^\\S+@\\S+\\.\\S+$", message = "Email must be valid")
        String email,

        @Size(max = 20, message = "Phone must have a maximum of 20 characters")
        String phone
) {
}
