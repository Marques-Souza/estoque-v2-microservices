package com.estoque.cadastro_service.service;

import com.estoque.cadastro_service.dto.ClientRequestDto;
import com.estoque.cadastro_service.dto.ClientRequestUpdateDto;
import com.estoque.cadastro_service.dto.ClientResponseDto;
import com.estoque.cadastro_service.exception.ClientAlreadyExistsException;
import com.estoque.cadastro_service.exception.ClientNotFoundException;
import com.estoque.cadastro_service.mapper.ClientMapper;
import com.estoque.cadastro_service.model.Client;
import com.estoque.cadastro_service.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientService clientService;

    private UUID id;
    private ClientRequestDto requestDto;
    private ClientRequestUpdateDto updateDto;
    private Client client;
    private ClientResponseDto responseDto;

    @BeforeEach
    void setUp(){
        id = UUID.randomUUID();
        requestDto = new ClientRequestDto("São Matheus paulista", "saopaulista@gmail.com", "6199999999", "08242206000130");
        updateDto = new ClientRequestUpdateDto("São Matheus", null, null);

        client = new Client();
        client.setName("São Matheus paulista");
        client.setEmail("saopaulista@gmail.com");
        client.setPhone("6199999999");
        client.setCnpj("08242206000130");

        responseDto = new ClientResponseDto(id, "São Matheus paulista","saopaulista@gmail.com", "6199999999", "08242206000130");
    }

    // ---------- CREATE ----------

    @Test
    void shouldThrowWhenEmailAlreadyExists(){
        when(clientRepository.existsByEmail(requestDto.email())).thenReturn(true);

        assertThrows(ClientAlreadyExistsException.class, () -> clientService.create(requestDto));

        verify(clientRepository, never()).save(any());
    }

    @Test
    void shouldThrowWhenCnpjAlreadyExists(){
        when(clientRepository.existsByCnpj(requestDto.cnpj())).thenReturn(true);

        assertThrows(ClientAlreadyExistsException.class, () -> clientService.create(requestDto));

        verify(clientRepository, never()).save(any());
    }

    @Test
    void shouldCreateClientWhenDataIsValid(){
        when(clientMapper.toEntity(requestDto)).thenReturn(client);
        when(clientRepository.save(client)).thenReturn(client);
        when(clientMapper.toResponse(client)).thenReturn(responseDto);

        ClientResponseDto result = clientService.create(requestDto);
        assertEquals(responseDto, result);
        verify(clientRepository).save(client);
    }

    // ---------- FIND BY ID ----------

    @Test
    void shouldReturnClientWhenIdExists(){
        when(clientRepository.findById(id)).thenReturn(Optional.of(client));
        when(clientMapper.toResponse(client)).thenReturn(responseDto);

        ClientResponseDto result = clientService.findById(id);
        assertEquals(responseDto, result);
    }

    @Test
    void shouldThrowNotFoundWhenIdDoesNotExist(){
        when(clientRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ClientNotFoundException.class, () -> clientService.findById(id));
    }

    // ---------- UPDATE ----------

    @Test
    void shouldThrowNotFoundWhenUpdatingNonexistentClient(){
        when(clientRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ClientNotFoundException.class, () -> clientService.update(id, updateDto));
        verify(clientMapper, never()).updateEntity(any(), any());
    }

    @Test
    void shouldUpdateClientWhenIdExists(){
        when(clientRepository.findById(id)).thenReturn(Optional.of(client));
        when(clientMapper.toResponse(client)).thenReturn(responseDto);
        when(clientRepository.save(client)).thenReturn(client);

        ClientResponseDto result = clientService.update(id, updateDto);

        assertEquals(responseDto, result);
        verify(clientMapper).updateEntity(updateDto, client);
    }
}
