package com.example.user_service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.user_service.common.enums.Gender;
import com.example.user_service.dto.client.ClientDTO;
import com.example.user_service.entity.Client;
import com.example.user_service.repository.ClientRepository;
import com.example.user_service.service.impl.ClientServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    void testGetClientById_found() {
        Client client = new Client(
            1,
            "Marco",
            Gender.M,
            30,
            "ID12345",
            "Calle Falsa 123",
            "555-111-1111",
            "1234",
            true
        );

        when(clientRepository.findById(1)).thenReturn(Optional.of(client));

        ClientDTO result = clientService.getClientById(1);

        assertNotNull(result);
        assertEquals("Marco", result.getName());
        verify(clientRepository, times(1)).findById(1);
    }
}
