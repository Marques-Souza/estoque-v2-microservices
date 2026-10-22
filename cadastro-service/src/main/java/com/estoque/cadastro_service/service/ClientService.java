package com.estoque.cadastro_service.service;

import com.estoque.cadastro_service.mapper.ClientMapper;
import com.estoque.cadastro_service.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

}
