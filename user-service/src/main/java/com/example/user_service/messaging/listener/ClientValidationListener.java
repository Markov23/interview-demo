package com.example.user_service.messaging.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import com.example.dto.ClientValidationRequest;
import com.example.dto.ClientValidationResponse;
import com.example.user_service.repository.ClientRepository;

@Service
public class ClientValidationListener {

    private final ClientRepository clientRepository;

    public ClientValidationListener(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @RabbitListener(queues = "client.validation.request")
    @SendTo
    public ClientValidationResponse handleValidation(ClientValidationRequest req) {
        boolean exists = clientRepository.existsById(req.clientId());
        System.out.println("Exist "+req.clientId()+"? " + exists);
        return new ClientValidationResponse(req.requestId(), req.clientId(), exists);
    }
}
