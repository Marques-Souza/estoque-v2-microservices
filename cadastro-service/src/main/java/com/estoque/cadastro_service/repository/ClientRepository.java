package com.estoque.cadastro_service.repository;

import com.estoque.cadastro_service.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    boolean existsByEmail(String email);
    boolean existsByCnpj(String cnpj);
}
