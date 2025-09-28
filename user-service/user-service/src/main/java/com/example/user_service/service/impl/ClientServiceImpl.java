package com.example.user_service.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.user_service.dto.client.ClientCreateDTO;
import com.example.user_service.dto.client.ClientDTO;
import com.example.user_service.dto.client.ClientUpdateDTO;
import com.example.user_service.entity.Client;
import com.example.user_service.repository.ClientRepository;
import com.example.user_service.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientDTO createClient(ClientCreateDTO client) {
        return ClientDTO.fromEntity(clientRepository.save(client.toEntity()));
    }

    public List<ClientDTO> getAllClients() {
        return ClientDTO.fromEntities(clientRepository.findAll());
    }

    public ClientDTO getClientById(Integer id) {
        return ClientDTO.fromEntity(clientRepository.findById(id).get());
    }
    
    public ClientDTO updateClient(Integer id, ClientUpdateDTO clientUpdateDTO) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));

        client.setName(clientUpdateDTO.getName());
        client.setGender(clientUpdateDTO.getGender());
        client.setAge(clientUpdateDTO.getAge());
        client.setIdentification(clientUpdateDTO.getIdentification());
        client.setAddress(clientUpdateDTO.getAddress());
        client.setPhoneNumber(clientUpdateDTO.getPhoneNumber());
        client.setPassword(clientUpdateDTO.getPassword());
        client.setState(clientUpdateDTO.getState());

        return ClientDTO.fromEntity(clientRepository.save(client));
    }

    public Integer deleteClient(Integer id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
        clientRepository.delete(client);

        return client.getId();
    }
}
