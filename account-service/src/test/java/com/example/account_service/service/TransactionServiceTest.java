package com.example.account_service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.account_service.common.enums.TransactionType;
import com.example.account_service.dto.transaction.TransactionDTO;
import com.example.account_service.entity.Account;
import com.example.account_service.entity.Transaction;
import com.example.account_service.repository.TransactionRepository;
import com.example.account_service.service.impl.TransactionServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    void testGetTransactionById_found() {
        Transaction transaction = new Transaction(
            1L,
            LocalDateTime.now(),
            TransactionType.DEPOSIT,
            100.00,
            1500.00,
            new Account()
        );

        when(transactionRepository.findById(transaction.getId())).thenReturn(Optional.of(transaction));

        TransactionDTO result = transactionService.getTransactionById(transaction.getId());

        assertNotNull(result);
        assertEquals(100.00, result.getValue());
        verify(transactionRepository, times(1)).findById(transaction.getId());
    }
}
