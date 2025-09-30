package com.example.account_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.account_service.dto.ResponseApi;
import com.example.account_service.dto.transaction.TransactionCreateDTO;
import com.example.account_service.dto.transaction.TransactionDTO;
import com.example.account_service.service.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Transactions")
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Operation(summary = "Create a new transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transaction created"),
            @ApiResponse(responseCode = "400", description = "Invalid transaction data"),
            @ApiResponse(responseCode = "404", description = "Transaction account not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<ResponseApi<TransactionDTO>> createTransaction(@RequestBody TransactionCreateDTO transactionCreateDTO) {
        TransactionDTO transaction = transactionService.createTransaction(transactionCreateDTO);

        ResponseApi<TransactionDTO> response = new ResponseApi<>("Transaction created successfully", transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get a list of all transactions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transactions list"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<ResponseApi<List<TransactionDTO>>> getAllTransactions() {
        ResponseApi<List<TransactionDTO>> response = new ResponseApi<>("Transactions retrieved successfully",
                transactionService.getAllTransactions());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get a transaction by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction information"),
            @ApiResponse(responseCode = "404", description = "Transaction not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseApi<TransactionDTO>> getTransactionById(@PathVariable Long id) {
        TransactionDTO transaction = transactionService.getTransactionById(id);

        ResponseApi<TransactionDTO> response = new ResponseApi<>("Transaction retrieved successfully", transaction);
        return ResponseEntity.ok(response);
    }
}
