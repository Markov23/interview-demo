package com.example.account_service.dto.account;

import com.example.account_service.entity.Account;

import lombok.Data;

@Data
public class AccountCreateDTO {
    private Long number;
    private String type;
    private Double initialBalance;
    private Boolean state;

    public Account toEntity() {
        Account account = new Account();
        account.setNumber(number);
        account.setType(this.type);
        account.setInitialBalance(this.initialBalance);
        account.setState(this.state);
        return account;
    }
}
