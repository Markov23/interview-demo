package com.example.account_service.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.account_service.dto.account.AccountCreateDTO;
import com.example.account_service.dto.account.AccountDTO;
import com.example.account_service.dto.account.AccountUpdateDTO;
import com.example.account_service.entity.Account;
import com.example.account_service.repository.AccountRepository;
import com.example.account_service.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountDTO createAccount(AccountCreateDTO account) {
        return AccountDTO.fromEntity(accountRepository.save(account.toEntity()));
    }

    public List<AccountDTO> getAllAccounts() {
        return AccountDTO.fromEntities(accountRepository.findAll());
    }

    public AccountDTO getAccountById(Long number) {
        return AccountDTO.fromEntity(accountRepository.findById(number).get());
    }
    
    public AccountDTO updateAccount(Long number, AccountUpdateDTO accountUpdateDTO) {
        Account account = accountRepository.findById(number)
                .orElseThrow(() -> new RuntimeException("Accounte no encontrado con numero: " + number));
        
        account.setType(accountUpdateDTO.getType());
        account.setInitialBalance(accountUpdateDTO.getInitialBalance());
        account.setState(accountUpdateDTO.getState());

        return AccountDTO.fromEntity(accountRepository.save(account));
    }

    public Long deleteAccount(Long number) {
        Account account = accountRepository.findById(number)
                .orElseThrow(() -> new RuntimeException("Account no encontrado con numero: " + number));
        accountRepository.delete(account);

        return account.getNumber();
    }
}
