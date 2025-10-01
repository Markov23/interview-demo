package com.example.user_service.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dto.AccountStatementResponse;
import com.example.user_service.common.enums.Gender;
import com.example.user_service.dto.client.ClientAccountStatementDTO;
import com.example.user_service.dto.client.ClientCreateDTO;
import com.example.user_service.dto.client.ClientDTO;
import com.example.user_service.dto.client.ClientUpdateDTO;
import com.example.user_service.entity.Client;
import com.example.user_service.exception.BadRequestException;
import com.example.user_service.exception.NotFoundException;
import com.example.user_service.messaging.producer.AccountStatementProducer;
import com.example.user_service.repository.ClientRepository;
import com.example.user_service.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final AccountStatementProducer accountStatementProducer;
    private final PasswordEncoder passwordEncoder;

    public ClientServiceImpl(
            ClientRepository clientRepository,
            AccountStatementProducer accountStatementProducer,
            PasswordEncoder passwordEncoder) {

        this.clientRepository = clientRepository;
        this.accountStatementProducer = accountStatementProducer;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ClientDTO createClient(ClientCreateDTO clientCreateDTO) {
        try {
            Gender.valueOf(clientCreateDTO.getGender().toUpperCase());
        } catch (Exception ex) {
            throw new BadRequestException("Invalid client gender: " + clientCreateDTO.getGender());
        }

        if (clientCreateDTO.getAge() < 18) {
            throw new BadRequestException("Minimum age 18");
        }

        Client client = clientCreateDTO.toEntity();
        client.setPassword(passwordEncoder.encode(clientCreateDTO.getPassword()));

        return ClientDTO.fromEntity(clientRepository.save(client));
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return ClientDTO.fromEntities(clientRepository.findAll());
    }

    @Override
    public ClientDTO getClientById(Integer id) {
        return ClientDTO.fromEntity(clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Client not found with id: " + id)));
    }

    @Override
    public ClientAccountStatementDTO getAccountStatementByClientId(
        Integer id,
        LocalDate startDate,
        LocalDate endDate) {

        ClientAccountStatementDTO accountStatement = ClientAccountStatementDTO.fromEntity(clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Client not found with id: " + id)));

        try {
            AccountStatementResponse response = accountStatementProducer.getAccountStatement(id, startDate, endDate);
            accountStatement.setDate(response.requestDate());
            accountStatement.setAccounts(response.accounts());
        } catch (Exception ex) {
            throw new BadRequestException("Something went wrong with another microservice, try later");
        }

        return accountStatement;
    }

    @Override
    public ClientDTO updateClient(Integer id, ClientUpdateDTO clientUpdateDTO) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Client not found with id: " + id));

        try {
            Gender.valueOf(clientUpdateDTO.getGender().toUpperCase());
        } catch (Exception ex) {
            throw new BadRequestException("Invalid client gender: " + clientUpdateDTO.getGender());
        }

        if (clientUpdateDTO.getAge() < 18) {
            throw new BadRequestException("Minimum age 18");
        }

        client.setName(clientUpdateDTO.getName());
        client.setGender(Gender.valueOf(clientUpdateDTO.getGender().toUpperCase()));
        client.setAge(clientUpdateDTO.getAge());
        client.setIdentification(clientUpdateDTO.getIdentification());
        client.setAddress(clientUpdateDTO.getAddress());
        client.setPhoneNumber(clientUpdateDTO.getPhoneNumber());

        return ClientDTO.fromEntity(clientRepository.save(client));
    }

    @Override
    public Integer deleteClient(Integer id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Client not found with id: " + id));
        client.setState(false);

        clientRepository.save(client);

        return client.getId();
    }
}
