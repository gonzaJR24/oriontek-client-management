package com.client.management.oriontek_client_management.command.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.client.management.oriontek_client_management.command.dto.CreateClientCommand;
import com.client.management.oriontek_client_management.command.dto.EditClientCommand;
import com.client.management.oriontek_client_management.domain.enums.ClientType;
import com.client.management.oriontek_client_management.domain.models.AddressEntity;
import com.client.management.oriontek_client_management.domain.models.ClientEntity;
import com.client.management.oriontek_client_management.repository.ClientRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ClientCommandService {
    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public ClientEntity handleCreate(CreateClientCommand command) {
        ClientEntity client = new ClientEntity();
        client.setName(command.name());
        client.setType(ClientType.valueOf(command.type().name().toUpperCase()));

        command.addresses().forEach(address -> {
            AddressEntity addressEntity = new AddressEntity(address.street(), address.city(), address.province(),
                    address.number(), address.country(),
                    client);
            client.getAddresses().add(addressEntity);
        });

        return clientRepository.save(client);
    }

    @Transactional
    public ClientEntity handleEdit(Long id, EditClientCommand command) {
        ClientEntity existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));

        existingClient.setName(command.name());
        existingClient.setType(ClientType.valueOf(command.type().name().toUpperCase()));

        clientRepository.save(existingClient);
        return existingClient;
    }

    @Transactional
    public void handleDelete(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new EntityNotFoundException("Cliente no encontrado para eliminar");
        }
        clientRepository.deleteById(id);
    }

}
