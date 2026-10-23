package com.estoque.cadastro_service.service;

import com.estoque.cadastro_service.dto.ClientRequestDto;
import com.estoque.cadastro_service.dto.ClientResponseDto;
import com.estoque.cadastro_service.exception.ClientAlreadyExistsException;
import com.estoque.cadastro_service.mapper.ClientMapper;
import com.estoque.cadastro_service.model.Client;
import com.estoque.cadastro_service.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Transactional
    public ClientResponseDto create(ClientRequestDto dto){
        if (clientRepository.existsByEmail(dto.email())){
            throw new ClientAlreadyExistsException("Email already registered.");
        }

        if (clientRepository.existsByCnpj(dto.cnpj())){
            throw new ClientAlreadyExistsException("CNPJ already registered.");
        }

        Client client = clientMapper.toEntity(dto);
        Client saved = clientRepository.save(client);
        return clientMapper.toResponse(saved);


    }

}
