package com.example.account_service.service;

import java.util.List;

import com.example.account_service.dto.transaction.TransactionCreateDTO;
import com.example.account_service.dto.transaction.TransactionDTO;

public interface TransactionService {
    public TransactionDTO createTransaction(TransactionCreateDTO transactionCreateDTO);

    public List<TransactionDTO> getAllTransactions();

    public TransactionDTO getTransactionById(Long number);
}
