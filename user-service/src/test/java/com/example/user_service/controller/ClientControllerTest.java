package com.example.user_service.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.user_service.common.constants.ApiConstants;
import com.example.user_service.dto.client.ClientDTO;
import com.example.user_service.service.ClientService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ClientController.class)
@AutoConfigureMockMvc(addFilters = false)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClientService clientService;

    @Test
    void testGetClientById() throws Exception {
        ClientDTO client = new ClientDTO(
            1,
            "Marco",
            "M",
            30,
            "ID12345",
            "Calle Falsa 123",
            "555-111-1111",
            true
        );
        when(clientService.getClientById(1)).thenReturn(client);

        mockMvc.perform(get(ApiConstants.API_CLIENT_URL + "/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.name").value("Marco"));
    }
}
