package com.client.management.oriontek_client_management.command.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.client.management.oriontek_client_management.command.dto.CreateClientCommand;
import com.client.management.oriontek_client_management.command.dto.EditClientCommand;
import com.client.management.oriontek_client_management.command.service.ClientCommandService;
import com.client.management.oriontek_client_management.domain.models.ClientEntity;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clients")
public class ClientCommandController {
    @Autowired
    private ClientCommandService clientService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateClientCommand client) {
        ClientEntity created = clientService.handleCreate(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @Valid @RequestBody EditClientCommand client) {
        ClientEntity updatedClient = clientService.handleEdit(id, client);
        return ResponseEntity.status(HttpStatus.OK).body(updatedClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        clientService.handleDelete(id);
        return ResponseEntity.noContent().build();
    }

}
