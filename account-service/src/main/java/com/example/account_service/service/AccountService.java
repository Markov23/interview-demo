package com.example.account_service.service;

import java.util.List;

import com.example.account_service.dto.account.AccountCreateDTO;
import com.example.account_service.dto.account.AccountDTO;
import com.example.account_service.dto.account.AccountUpdateDTO;

public interface AccountService {
    public AccountDTO createAccount(AccountCreateDTO account);

    public List<AccountDTO> getAllAccounts();

    public AccountDTO getAccountById(String id);

    public AccountDTO updateAccount(String id, AccountUpdateDTO accountUpdateDTO);
}
