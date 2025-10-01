package com.example.account_service.messaging.producer;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.constants.RabbitConstants;
import com.example.dto.ClientValidationRequest;
import com.example.dto.ClientValidationResponse;

@Service
public class ClientValidationProducer {
    private final RabbitTemplate rabbitTemplate;
    private final DirectExchange validationExchange;

    public ClientValidationProducer(
        RabbitTemplate rabbitTemplate, 
        @Qualifier("clientValidationExchange") DirectExchange validationExchange
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.validationExchange = validationExchange;
    }

    public boolean validateClient(Integer clientId) {
        ClientValidationRequest req = new ClientValidationRequest(clientId);

        ClientValidationResponse res = (ClientValidationResponse) rabbitTemplate.convertSendAndReceive(
            validationExchange.getName(),
            RabbitConstants.CLIENT_VALIDATION_BIND,
            req
        );

        if (res == null) {
            throw new RuntimeException("Timeout/No response from user-service");
        }

        return res.exists();
    }
}
