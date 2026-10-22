package com.estoque.cadastro_service.dto;

import java.util.UUID;

public record ClientResponseDto(

        UUID id,
        String name,
        String email,
        String phone,
        String cnpj
) {
}
