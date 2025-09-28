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

import com.example.account_service.dto.account.AccountCreateDTO;
import com.example.account_service.dto.account.AccountDTO;
import com.example.account_service.dto.account.AccountUpdateDTO;
import com.example.account_service.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public AccountDTO createAccount(@RequestBody AccountCreateDTO accountCreateDTO) {
        return accountService.createAccount(accountCreateDTO);
    }

    @GetMapping
    public List<AccountDTO> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public AccountDTO getAccountById(@PathVariable Long number) {
        return accountService.getAccountById(number);
    }

    @PutMapping("/{id}")
    public AccountDTO updateAccount(@PathVariable Long number, @RequestBody AccountUpdateDTO accountUpdateDTO) {
        return accountService.updateAccount(number, accountUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public Long deleteAccount(@PathVariable Long number) {
        return accountService.deleteAccount(number);
    }
}
