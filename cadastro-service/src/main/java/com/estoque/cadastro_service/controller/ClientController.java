package com.estoque.cadastro_service.controller;

import com.estoque.cadastro_service.dto.ClientRequestDto;
import com.estoque.cadastro_service.dto.ClientResponseDto;
import com.estoque.cadastro_service.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping
    public ResponseEntity<Page<ClientResponseDto>> findAll(
            @RequestHeader(value = "search", required = false) String search,
            @PageableDefault(page = 0, size = 10, sort = "name")Pageable pageable){
        Page<ClientResponseDto> response = clientService.findAll(search, pageable);
        return ResponseEntity.ok(response);
    }
}
