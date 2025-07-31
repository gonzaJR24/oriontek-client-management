package com.client.management.command.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.client.management.oriontek_client_management.command.dto.CreateClientCommand;
import com.client.management.oriontek_client_management.command.dto.EditClientCommand;
import com.client.management.oriontek_client_management.command.service.ClientCommandService;
import com.client.management.oriontek_client_management.domain.enums.ClientType;
import com.client.management.oriontek_client_management.domain.models.ClientEntity;
import com.client.management.oriontek_client_management.repository.ClientRepository;

public class ClientCommandServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientCommandService clientCommandService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHandleCreate() {
        // Given
        ClientEntity mockEntity = new ClientEntity();
        mockEntity.setId(1L);
        mockEntity.setName("Prueba cliente");
        mockEntity.setType(ClientType.PERSONA_FISICA);
        mockEntity.setAddresses(Collections.emptyList());

        CreateClientCommand command = new CreateClientCommand("Prueba cliente", ClientType.PERSONA_FISICA,
                Collections.emptyList());

        when(clientRepository.save(any(ClientEntity.class))).thenReturn(mockEntity);

        // Then
        ClientEntity clientEntity = clientCommandService.handleCreate(command);

        // When
        assertEquals("Prueba cliente", clientEntity.getName());
        assertEquals(ClientType.PERSONA_FISICA, clientEntity.getType());

        verify(clientRepository).save(any(ClientEntity.class));
    }

    @Test
    public void testHandleEdit() {
        // Given
        ClientEntity mockEntity = new ClientEntity();
        mockEntity.setId(1L);
        mockEntity.setName("Antiguo nombre");
        mockEntity.setType(ClientType.PERSONA_FISICA);

        Long clienteId = 1L;

        EditClientCommand editCommand = new EditClientCommand(
                ClientType.EMPRESA, "Nuevo Cliente", Collections.emptyList());

        when(clientRepository.findById(clienteId)).thenReturn(Optional.of(mockEntity));
        when(clientRepository.save(any(ClientEntity.class))).thenReturn(mockEntity);

        // When
        ClientEntity result = clientCommandService.handleEdit(clienteId, editCommand);

        // Then
        assertEquals("Nuevo Cliente", result.getName());
        assertEquals(ClientType.EMPRESA, result.getType());

        verify(clientRepository).findById(clienteId);
        verify(clientRepository).save(any(ClientEntity.class));
    }

    @Test
    public void testHandleDelete() {
        Long cliente_id = 1L;

        when(clientRepository.existsById(cliente_id)).thenReturn(true);
        doNothing().when(clientRepository).deleteById(cliente_id);

        clientCommandService.handleDelete(cliente_id);

        verify(clientRepository).deleteById(cliente_id);
    }

}
