package com.example.account_service.dto.account;

import com.example.account_service.common.enums.AccountType;
import com.example.account_service.entity.Account;

import lombok.Data;

@Data
public class AccountCreateDTO {
    private String type;
    private Double initialBalance;
    private Integer clientId;

    public Account toEntity() {
        Account account = new Account();
        account.setType(AccountType.valueOf(this.getType().toUpperCase()));
        account.setInitialBalance(this.initialBalance);
        account.setClientId(this.clientId);
        return account;
    }
}
