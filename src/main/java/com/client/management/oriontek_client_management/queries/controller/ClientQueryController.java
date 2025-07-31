package com.client.management.oriontek_client_management.queries.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.client.management.oriontek_client_management.queries.dto.ClientView;
import com.client.management.oriontek_client_management.queries.service.ClientQueryService;

@RestController
@RequestMapping("/api/clients")

public class ClientQueryController {
    @Autowired
    private ClientQueryService service;

    @GetMapping
    public List<ClientView> getAllClients() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ClientView getClient(@PathVariable Long id) {
        return service.findById(id);
    }
}

