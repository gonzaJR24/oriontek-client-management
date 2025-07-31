package com.client.management.oriontek_client_management.queries.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.client.management.oriontek_client_management.domain.models.ClientEntity;
import com.client.management.oriontek_client_management.queries.dto.AddressView;
import com.client.management.oriontek_client_management.queries.dto.ClientView;
import com.client.management.oriontek_client_management.repository.ClientRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientQueryService {
    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public List<ClientView> findAll() {
        return repository.findAll().stream().map(client->toClientView(client)).toList();
    }

    @Transactional(readOnly = true)
    public ClientView findById(Long id) {
        ClientEntity clientEntity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return toClientView(clientEntity);
    }

    public ClientView toClientView(ClientEntity clientEntity) {
        List<AddressView> addressViews = clientEntity.getAddresses().stream()
                .map(address -> new AddressView(address.getCountry(), address.getProvince(), address.getCity(),
                        address.getStreet(), address.getNumber()))
                .toList();
        return new ClientView(clientEntity.getId(), clientEntity.getName(), clientEntity.getType(), addressViews);
    }
}