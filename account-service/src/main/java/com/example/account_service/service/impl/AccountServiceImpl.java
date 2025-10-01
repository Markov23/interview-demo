package com.example.account_service.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.account_service.common.constants.AccountConstants;
import com.example.account_service.common.constants.DateConstants;
import com.example.account_service.common.enums.AccountType;
import com.example.account_service.dto.account.AccountCreateDTO;
import com.example.account_service.dto.account.AccountDTO;
import com.example.account_service.dto.account.AccountTransactionsDTO;
import com.example.account_service.dto.account.AccountUpdateDTO;
import com.example.account_service.entity.Account;
import com.example.account_service.exception.BadRequestException;
import com.example.account_service.exception.NotFoundException;
import com.example.account_service.messaging.producer.ClientValidationProducer;
import com.example.account_service.repository.AccountRepository;
import com.example.account_service.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final ClientValidationProducer clientValidationProducer;

    public AccountServiceImpl(AccountRepository accountRepository, ClientValidationProducer clientValidationProducer) {
        this.accountRepository = accountRepository;
        this.clientValidationProducer = clientValidationProducer;
    }

    @Override
    public AccountDTO createAccount(AccountCreateDTO accountCreateDTO) {
        if (accountCreateDTO.getInitialBalance() < 0) {
            throw new BadRequestException("Initial balance must be positive");
        }

        try {
            AccountType.valueOf(accountCreateDTO.getType().toUpperCase());
        } catch (Exception ex) {
            throw new BadRequestException("Invalid account type: " + accountCreateDTO.getType());
        }

        if (!clientValidationProducer.validateClient(accountCreateDTO.getClientId())) {
            throw new NotFoundException("Client not found with id: " + accountCreateDTO.getClientId());
        }

        Account account = accountCreateDTO.toEntity();
        account.setNumber(generateAccountNumber());

        return AccountDTO.fromEntity(accountRepository.save(account));
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
    public List<AccountTransactionsDTO> getAccountsByClientIdWithTransactions(Integer clientId, LocalDate starDate,
            LocalDate endDate) {

        if (starDate == null) {
            starDate = LocalDate.of(DateConstants.MINIMUM_YEAR, DateConstants.MINIMUM_MONTH, DateConstants.MINIMUM_DAY);
        }

        if (endDate == null) {
            endDate = LocalDate.now();
        }

        return AccountTransactionsDTO
                .fromEntities(accountRepository.findByClientIdWithTransactions(clientId, starDate.atStartOfDay(),
                        endDate.atTime(DateConstants.MAX_HOUR, DateConstants.MAX_MINUTE, DateConstants.MAX_SECOND)));
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
        String prefix = AccountConstants.ACCOUNT_NUMBER_PREFIX;
        int length = AccountConstants.ACCOUNT_NUMBER_LENGTH;

        String numericPart = String.format("%0" + (length - prefix.length()) + "d", next);
        return prefix + numericPart;
    }
}
