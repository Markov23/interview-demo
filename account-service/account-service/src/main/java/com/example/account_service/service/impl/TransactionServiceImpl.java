package com.example.account_service.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.account_service.dto.transaction.TransactionCreateDTO;
import com.example.account_service.dto.transaction.TransactionDTO;
import com.example.account_service.dto.transaction.TransactionUpdateDTO;
import com.example.account_service.entity.Transaction;
import com.example.account_service.repository.TransactionRepository;

@Service
public class TransactionServiceImpl {
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionDTO createTransaction(TransactionCreateDTO transaction) {
        return TransactionDTO.fromEntity(transactionRepository.save(transaction.toEntity()));
    }

    public List<TransactionDTO> getAllTransactions() {
        return TransactionDTO.fromEntities(transactionRepository.findAll());
    }

    public TransactionDTO getTransactionById(Long id) {
        return TransactionDTO.fromEntity(transactionRepository.findById(id).get());
    }
    
    public TransactionDTO updateTransaction(Long id, TransactionUpdateDTO transactionUpdateDTO) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transactione no encontrado con id: " + id));
        
        transaction.setDate(transactionUpdateDTO.getDate());
        transaction.setType(transactionUpdateDTO.getType());
        transaction.setValue(transactionUpdateDTO.getValue());
        transaction.setBalance(transactionUpdateDTO.getBalance());

        return TransactionDTO.fromEntity(transactionRepository.save(transaction));
    }

    public Long deleteTransaction(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction no encontrado con id: " + id));
        transactionRepository.delete(transaction);

        return transaction.getId();
    }
}
