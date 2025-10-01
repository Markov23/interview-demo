package com.example.user_service.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.example.user_service.common.constants.ApiConstants;
import com.example.user_service.common.enums.Gender;
import com.example.user_service.entity.Client;
import com.example.user_service.repository.ClientRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc(addFilters = false)
class ClientIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientRepository clientRepository;

    @BeforeEach
    void setup() {
        clientRepository.deleteAll();
    }

    @Test
    void testCreateClient() throws Exception {
        String clientJson = """
            {
                "name": "Marco",
                "gender": "M",
                "age": 26,
                "identification": "ID23079",
                "address": "Jalisco",
                "phoneNumber": "831-114-4705",
                "password": "12345"
            }""";

        mockMvc.perform(post(ApiConstants.API_CLIENT_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(clientJson))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.data.id").exists())
            .andExpect(jsonPath("$.data.name").value("Marco"));
    }

    @Test
    void testGetClientById() throws Exception {
        Client client = clientRepository.save(new Client(
            "Marco",
            Gender.M,
            30,
            "ID12345",
            "Calle Falsa 123",
            "555-111-1111",
            "1234",
            true
        ));

        mockMvc.perform(get(ApiConstants.API_CLIENT_URL + "/{id}", client.getId()))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.name").value("Marco"));
    }

    @Test
    @Transactional
    void testGetAllClients() throws Exception {
        clientRepository.save(new Client(
            "Marco",
            Gender.M,
            30,
            "ID12345",
            "Calle Falsa 123",
            "555-111-1111",
            "1234",
            true
        ));

        clientRepository.save(new Client(
            "Maria",
            Gender.F,
            30,
            "ID67890",
            "Calle Falsa 456",
            "555-111-2222",
            "5678",
            true
        ));

        mockMvc.perform(get(ApiConstants.API_CLIENT_URL)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.length()").value(2))
            .andExpect(jsonPath("$.data[0].name").value("Marco"))
            .andExpect(jsonPath("$.data[1].name").value("Maria"))
            .andDo(print());
    }

    @Test
    void testUpdateClient() throws Exception {

        Client client = clientRepository.save(new Client(
            "Marco",
            Gender.M,
            30,
            "ID12345",
            "Calle Falsa 123",
            "555-111-1111",
            "1234",
            true
        ));

        String updateJson = """
            {
                "name": "Marco Nava",
                "gender": "M",
                "age": 26,
                "identification": "ID23079",
                "address": "Jalisco",
                "phoneNumber": "831-114-4705"
            }
        """;

        mockMvc.perform(put(ApiConstants.API_CLIENT_URL + "/{id}", client.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(updateJson))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.name").value("Marco Nava"));
    }

    @Test
    void testDeleteClient() throws Exception {

        Client client = clientRepository.save(new Client(
            "Marco",
            Gender.M,
            30,
            "ID12345",
            "Calle Falsa 123",
            "555-111-1111",
            "1234",
            true
        ));

        mockMvc.perform(delete(ApiConstants.API_CLIENT_URL + "/{id}", client.getId()))
            .andExpect(status().isOk());

        mockMvc.perform(get(ApiConstants.API_CLIENT_URL + "/{id}", client.getId()))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.state").value(false));
    }
}