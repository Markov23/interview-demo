package com.example.user_service.service;

import java.util.List;

import com.example.user_service.dto.client.ClientCreateDTO;
import com.example.user_service.dto.client.ClientDTO;
import com.example.user_service.dto.client.ClientUpdateDTO;

public interface ClientService {

    public ClientDTO createClient(ClientCreateDTO clientCreateDTO);

    public List<ClientDTO> getAllClients();

    public ClientDTO getClientById(Integer id);

    public ClientDTO updateClient(Integer id, ClientUpdateDTO clientUpdateDTO);
    
    public Integer deleteClient(Integer id);
}
