package com.estoque.cadastro_service.mapper;

import com.estoque.cadastro_service.dto.ClientRequestDto;
import com.estoque.cadastro_service.dto.ClientRequestUpdateDto;
import com.estoque.cadastro_service.dto.ClientResponseDto;
import com.estoque.cadastro_service.model.Client;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "id", ignore = true)
    Client toEntity(ClientRequestDto dto);

    ClientResponseDto toResponse(Client client);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cnpj", ignore = true)
    void updateEntity(ClientRequestUpdateDto dto, @MappingTarget Client client);
}
