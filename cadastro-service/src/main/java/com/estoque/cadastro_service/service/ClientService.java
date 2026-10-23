package com.estoque.cadastro_service.service;

import com.estoque.cadastro_service.dto.ClientRequestDto;
import com.estoque.cadastro_service.dto.ClientResponseDto;
import com.estoque.cadastro_service.exception.ClientAlreadyExistsException;
import com.estoque.cadastro_service.exception.ClientNotFoundException;
import com.estoque.cadastro_service.mapper.ClientMapper;
import com.estoque.cadastro_service.model.Client;
import com.estoque.cadastro_service.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

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

    @Transactional(readOnly = true)
    public ClientResponseDto findById(UUID id){
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found."));
        return  clientMapper.toResponse(client);
    }


    @Transactional(readOnly = true)
    public Page<ClientResponseDto> findAll(String search, Pageable pageable){
        Page<Client> clients = clientRepository.findAll(pageable);

        if (search != null && !search.isBlank()){
            clients = clientRepository.findBySearch(search, pageable);
        }
        return clients.map(clientMapper::toResponse);
    }

}
