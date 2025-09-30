package com.example.account_service.messaging.producer;

import java.util.UUID;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.example.dto.ClientValidationRequest;
import com.example.dto.ClientValidationResponse;

@Service
public class ClientValidationProducer {
    private final RabbitTemplate rabbitTemplate;
    private final DirectExchange validationExchange;

    public ClientValidationProducer(RabbitTemplate rabbitTemplate, DirectExchange validationExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.validationExchange = validationExchange;
    }

    public boolean validateClient(Integer clientId) {
        String requestId = UUID.randomUUID().toString();
        ClientValidationRequest req = new ClientValidationRequest(requestId, clientId);

        ClientValidationResponse res = (ClientValidationResponse) rabbitTemplate.convertSendAndReceive(
                validationExchange.getName(),
                "client.validate",
                req
        );

        if (res == null) {
            throw new RuntimeException("Timeout/No response from user-service");
        }

        System.out.println("Respuesta recibida: " + res);
        return res.exists();
    }
}
