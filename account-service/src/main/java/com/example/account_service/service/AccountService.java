package com.example.account_service.service;

import java.time.LocalDate;
import java.util.List;

import com.example.account_service.dto.account.AccountCreateDTO;
import com.example.account_service.dto.account.AccountDTO;
import com.example.account_service.dto.account.AccountTransactionsDTO;
import com.example.account_service.dto.account.AccountUpdateDTO;

public interface AccountService {
    public AccountDTO createAccount(AccountCreateDTO accountCreateDTO);

    public List<AccountDTO> getAllAccounts();

    public AccountDTO getAccountById(String id);

    public List<AccountTransactionsDTO> getAccountsByClientIdWithTransactions(Integer clientId, LocalDate starDate,
            LocalDate endDate);

    public AccountDTO updateAccount(String id, AccountUpdateDTO accountUpdateDTO);
}
