package com.example.account_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.account_service.dto.ResponseApi;
import com.example.account_service.dto.account.AccountCreateDTO;
import com.example.account_service.dto.account.AccountDTO;
import com.example.account_service.dto.account.AccountUpdateDTO;
import com.example.account_service.service.AccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Accounts")
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Operation(summary = "Create a new account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Account created"),
            @ApiResponse(responseCode = "400", description = "Invalid account data"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<ResponseApi<AccountDTO>> createAccount(@RequestBody AccountCreateDTO accountCreateDTO) {
        AccountDTO account = accountService.createAccount(accountCreateDTO);

        ResponseApi<AccountDTO> response = new ResponseApi<>("Account created successfully", account);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get a list of all accounts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accounts list"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<ResponseApi<List<AccountDTO>>> getAllAccounts() {
        ResponseApi<List<AccountDTO>> response = new ResponseApi<>("Accounts retrieved successfully",
                accountService.getAllAccounts());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get a account by account number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account information"),
            @ApiResponse(responseCode = "404", description = "Account not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseApi<AccountDTO>> getAccountById(@PathVariable String id) {
        AccountDTO account = accountService.getAccountById(id);

        ResponseApi<AccountDTO> response = new ResponseApi<>("Account retrieved successfully", account);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update an account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account updated"),
            @ApiResponse(responseCode = "404", description = "Account not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResponseApi<AccountDTO>> updateAccount(@PathVariable String id,
            @RequestBody AccountUpdateDTO accountUpdateDTO) {
        AccountDTO account = accountService.updateAccount(id, accountUpdateDTO);

        ResponseApi<AccountDTO> response = new ResponseApi<>("Account successfully update", account);
        return ResponseEntity.ok(response);
    }
}
