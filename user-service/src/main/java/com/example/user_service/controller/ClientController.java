package com.example.user_service.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ClientDTO createClient(@RequestBody ClientCreateDTO clientCreateDTO) {
        return clientService.createClient(clientCreateDTO);
    }

    @GetMapping
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClients();
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
