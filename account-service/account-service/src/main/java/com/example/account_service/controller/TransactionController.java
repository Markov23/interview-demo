package com.example.account_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.account_service.dto.transaction.TransactionCreateDTO;
import com.example.account_service.dto.transaction.TransactionDTO;
import com.example.account_service.dto.transaction.TransactionUpdateDTO;
import com.example.account_service.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public TransactionDTO createTransaction(@RequestBody TransactionCreateDTO transactionCreateDTO) {
        return transactionService.createTransaction(transactionCreateDTO);
    }

    @GetMapping
    public List<TransactionDTO> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public TransactionDTO getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }

    @PutMapping("/{id}")
    public TransactionDTO updateTransaction(@PathVariable Long id, @RequestBody TransactionUpdateDTO transactionUpdateDTO) {
        return transactionService.updateTransaction(id, transactionUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public Long deleteTransaction(@PathVariable Long id) {
        return transactionService.deleteTransaction(id);
    }
}
