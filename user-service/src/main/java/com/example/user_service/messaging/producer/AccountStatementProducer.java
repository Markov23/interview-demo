package com.example.user_service.messaging.producer;

import java.time.LocalDate;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.constants.RabbitConstants;
import com.example.dto.AccountStatementRequest;
import com.example.dto.AccountStatementResponse;

@Service
public class AccountStatementProducer {
    private final RabbitTemplate rabbitTemplate;
    private final DirectExchange statementExchange;

    public AccountStatementProducer(
        RabbitTemplate rabbitTemplate, 
        @Qualifier("accountStatementExchange") DirectExchange statementExchange
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.statementExchange = statementExchange;
    }

    public AccountStatementResponse getAccountStatement(Integer clientId, LocalDate startDate, LocalDate endDate) {
        AccountStatementRequest req = new AccountStatementRequest(clientId, startDate, endDate);

        AccountStatementResponse res = (AccountStatementResponse) rabbitTemplate.convertSendAndReceive(
                statementExchange.getName(),
                RabbitConstants.ACCOUNT_STATEMENT_BIND,
                req
        );

        if (res == null) {
            throw new RuntimeException("Timeout/No response from user-service");
        }

        return res;
    }
}
