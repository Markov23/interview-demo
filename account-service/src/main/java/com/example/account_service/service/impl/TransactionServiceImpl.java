package com.example.account_service.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.account_service.common.enums.TransactionType;
import com.example.account_service.dto.transaction.TransactionCreateDTO;
import com.example.account_service.dto.transaction.TransactionDTO;
import com.example.account_service.entity.Account;
import com.example.account_service.entity.Transaction;
import com.example.account_service.exception.BadRequestException;
import com.example.account_service.exception.NotFoundException;
import com.example.account_service.repository.AccountRepository;
import com.example.account_service.repository.TransactionRepository;
import com.example.account_service.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public TransactionDTO createTransaction(TransactionCreateDTO transactionCreateDTO) {
        Account account = accountRepository.findById(transactionCreateDTO.getAccountNumber()).orElseThrow(
                () -> new NotFoundException("Account not found with number: " + transactionCreateDTO.getAccountNumber()));

        if(transactionCreateDTO.getValue() == 0) {
            throw new BadRequestException("The transaction value must be different from 0");
        }

        Double newBalance = account.getInitialBalance() + transactionCreateDTO.getValue();
        if (newBalance < 0) {
            throw new BadRequestException("Insufficient funds");
        }

        try {
            TransactionType.valueOf(transactionCreateDTO.getType().toUpperCase());
        } catch(Exception ex) {
            throw new BadRequestException("Invalid transaction type: " + transactionCreateDTO.getType());
        }

        account.setInitialBalance(newBalance);
        accountRepository.save(account);

        Transaction transaction = transactionCreateDTO.toEntity();
        transaction.setAccount(account);
        transaction.setBalance(newBalance);
        transaction.setDate(LocalDateTime.now());

        return TransactionDTO.fromEntity(transactionRepository.save(transaction));
    }

    @Override
    public List<TransactionDTO> getAllTransactions() {
        return TransactionDTO.fromEntities(transactionRepository.findAll());
    }

    @Override
    public TransactionDTO getTransactionById(Long id) {
        return TransactionDTO.fromEntity(transactionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Transaction not found with id: " + id)));
    }
}
