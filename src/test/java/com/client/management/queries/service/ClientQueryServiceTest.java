package com.client.management.queries.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.client.management.oriontek_client_management.domain.enums.ClientType;
import com.client.management.oriontek_client_management.domain.models.ClientEntity;
import com.client.management.oriontek_client_management.queries.dto.ClientView;
import com.client.management.oriontek_client_management.queries.service.ClientQueryService;
import com.client.management.oriontek_client_management.repository.ClientRepository;

public class ClientQueryServiceTest {
    @Mock
    private ClientRepository repository;

    @InjectMocks
    private ClientQueryService clientQueryService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll(){
        //Given
        ClientEntity fakeClient=new ClientEntity();
        fakeClient.setId(1L);
        fakeClient.setName("Test Client");
        fakeClient.setType(ClientType.PERSONA_FISICA);
        fakeClient.setAddresses(Collections.emptyList());

        when(repository.findAll()).thenReturn(List.of(fakeClient));

        //When
        List<ClientView> result = clientQueryService.findAll();

        // Then
        ClientView clientView = result.get(0);
        assertEquals("Test Client", clientView.name());
        assertEquals(ClientType.PERSONA_FISICA, clientView.type());
        assertTrue(clientView.addresses().isEmpty());
    }


    @Test
    public void testFindById(){
        //Given
        Long clientId=1L;

        ClientEntity fakeClient=new ClientEntity();
        fakeClient.setId(1L);
        fakeClient.setName("Prueba cliente");
        fakeClient.setType(ClientType.PERSONA_FISICA);
        fakeClient.setAddresses(Collections.emptyList());

        when(repository.findById(clientId)).thenReturn(Optional.of(fakeClient));

        //When
        ClientView clientView=clientQueryService.findById(clientId);

        //Then
        assertNotNull(clientView);
        assertEquals(clientId, clientView.id());
        assertEquals("Prueba cliente",clientView.name());
        assertEquals(ClientType.PERSONA_FISICA, clientView.type());

    }

}
