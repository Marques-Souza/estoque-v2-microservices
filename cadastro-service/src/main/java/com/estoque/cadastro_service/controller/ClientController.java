package com.estoque.cadastro_service.controller;

import com.estoque.cadastro_service.dto.ClientRequestDto;
import com.estoque.cadastro_service.dto.ClientResponseDto;
import com.estoque.cadastro_service.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponseDto> create(@Valid @RequestBody ClientRequestDto dto){
        ClientResponseDto response = clientService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDto> findById(@PathVariable UUID id){
        ClientResponseDto response = clientService.findById(id);
        return ResponseEntity.ok(response);
    }
}
