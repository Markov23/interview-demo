package com.example.account_service.controller;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.account_service.common.constants.ApiConstants;
import com.example.account_service.dto.transaction.TransactionDTO;
import com.example.account_service.service.TransactionService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(TransactionController.class)
@AutoConfigureMockMvc(addFilters = false)
public class TransactionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TransactionService transactionService;

    @Test
    void testGetTransactionById() throws Exception {
        TransactionDTO transaction = new TransactionDTO(
            1L,
            LocalDateTime.now(),
            "DEPOSIT",
            100.00,
            1500.00,
            "1001000003"
        );
        when(transactionService.getTransactionById(1L)).thenReturn(transaction);

        mockMvc.perform(get(ApiConstants.API_TRANSACTIONS_URL + "/{id}", transaction.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.accountNumber").value(transaction.getAccountNumber()));
    }
}
