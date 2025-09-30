package com.example.account_service.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.account_service.common.enums.AccountType;
import com.example.account_service.dto.account.AccountCreateDTO;
import com.example.account_service.dto.account.AccountDTO;
import com.example.account_service.dto.account.AccountUpdateDTO;
import com.example.account_service.entity.Account;
import com.example.account_service.exception.BadRequestException;
import com.example.account_service.exception.NotFoundException;
import com.example.account_service.messaging.producer.ClientValidationProducer;
import com.example.account_service.repository.AccountRepository;
import com.example.account_service.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
    private static final String PREFIX = "1001";
    private static final int TOTAL_LENGTH = 10;
    private final AccountRepository accountRepository;
    private final ClientValidationProducer clientValidationProducer;

    public AccountServiceImpl(AccountRepository accountRepository, ClientValidationProducer clientValidationProducer) {
        this.accountRepository = accountRepository;
        this.clientValidationProducer = clientValidationProducer;
    }

    @Override
    public AccountDTO createAccount(AccountCreateDTO account) {
        if(account.getInitialBalance() < 0) {
            throw new BadRequestException("Initial balance must be positive");
        }

        try {
            AccountType.valueOf(account.getType().toUpperCase());
        } catch(Exception ex) {
            throw new BadRequestException("Invalid account type: " + account.getType());
        }

        if (!clientValidationProducer.validateClient(account.getClientId())) {
            throw new NotFoundException("Client not found with id: " + account.getClientId());
        }

        Account accountEntity = account.toEntity();
        accountEntity.setNumber(generateAccountNumber());

        return AccountDTO.fromEntity(accountRepository.save(accountEntity));
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        return AccountDTO.fromEntities(accountRepository.findAll());
    }

    @Override
    public AccountDTO getAccountById(String id) {
        return AccountDTO.fromEntity(accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Account not found with account number: " + id)));
    }

    @Override
    public AccountDTO updateAccount(String id, AccountUpdateDTO accountUpdateDTO) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Account not found with account number: " + id));
        
        account.setState(accountUpdateDTO.getState());

        return AccountDTO.fromEntity(accountRepository.save(account));
    }

    private String generateAccountNumber() {
        Long next = accountRepository.count() + 1;
        String numericPart = String.format("%0" + (TOTAL_LENGTH - PREFIX.length()) + "d", next);
        return PREFIX + numericPart;
    }
}
