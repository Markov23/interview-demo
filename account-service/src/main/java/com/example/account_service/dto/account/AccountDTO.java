package com.example.account_service.dto.account;

import java.util.List;
import java.util.stream.Collectors;

import com.example.account_service.entity.Account;

import lombok.Data;

@Data
public class AccountDTO {
    private String number;
    private String type;
    private Double initialBalance;
    private Boolean state;
    private Integer clientId;

    public static List<AccountDTO> fromEntities(List<Account> accounts) {
        return accounts.stream().map(account -> AccountDTO.fromEntity(account)).collect(Collectors.toList());
    }

    public static AccountDTO fromEntity(Account account) {
        AccountDTO dto = new AccountDTO();
        dto.setNumber(account.getNumber());
        dto.setType(account.getType().toString());
        dto.setInitialBalance(account.getInitialBalance());
        dto.setState(account.getState());
        dto.setClientId(account.getClientId());
        return dto;
    }
}
