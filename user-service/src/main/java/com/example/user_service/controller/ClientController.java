package com.example.user_service.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user_service.dto.ApiResponse;
import com.example.user_service.dto.client.ClientCreateDTO;
import com.example.user_service.dto.client.ClientDTO;
import com.example.user_service.dto.client.ClientUpdateDTO;
import com.example.user_service.service.ClientService;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ClientDTO>> createClient(@RequestBody ClientCreateDTO clientCreateDTO) {
        try {
            ApiResponse<ClientDTO> response = new ApiResponse<>("Client created successfully",
                    clientService.createClient(clientCreateDTO));
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>("Try later"));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ClientDTO>>> getAllClients() {
        try {
            ApiResponse<List<ClientDTO>> response = new ApiResponse<>("Clients retrieved successfully", clientService.getAllClients());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>("Try later"));
        }
    }

    @GetMapping("/{id}")
    public ClientDTO getClientById(@PathVariable Integer id) {
        return clientService.getClientById(id);
    }

    @PutMapping("/{id}")
    public ClientDTO updateClient(@PathVariable Integer id, @RequestBody ClientUpdateDTO clientUpdateDTO) {
        return clientService.updateClient(id, clientUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public Integer deleteClient(@PathVariable Integer id) {
        return clientService.deleteClient(id);
    }
}
