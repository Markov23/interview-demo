package com.example.account_service.service;

import java.util.List;

import com.example.account_service.dto.transaction.TransactionCreateDTO;
import com.example.account_service.dto.transaction.TransactionDTO;
import com.example.account_service.dto.transaction.TransactionUpdateDTO;

public interface TransactionService {
    public TransactionDTO createTransaction(TransactionCreateDTO transaction);

    public List<TransactionDTO> getAllTransactions();

    public TransactionDTO getTransactionById(Long number);

    public TransactionDTO updateTransaction(Long number, TransactionUpdateDTO transactionUpdateDTO);
    
    public Long deleteTransaction(Long number);
}
