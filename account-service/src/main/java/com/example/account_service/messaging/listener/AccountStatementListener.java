package com.example.account_service.messaging.listener;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import com.example.account_service.dto.account.AccountTransactionsDTO;
import com.example.account_service.service.AccountService;
import com.example.constants.RabbitConstants;
import com.example.dto.AccountStatementRequest;
import com.example.dto.AccountStatementResponse;

@Service
public class AccountStatementListener {
    private final AccountService accountService;

    public AccountStatementListener(AccountService accountService) {
        this.accountService = accountService;
    }

    @RabbitListener(queues = RabbitConstants.ACCOUNT_STATEMENT_QUEUE)
    @SendTo
    public AccountStatementResponse handleValidation(AccountStatementRequest req) {
        return new AccountStatementResponse(
            LocalDateTime.now(), 
            AccountTransactionsDTO.fromDTOsToMessagings(
                accountService.getAccountsByClientIdWithTransactions(
                    req.clientId(), 
                    req.startDate(), 
                    req.endDate()
                )
            )
        );
    }
}
