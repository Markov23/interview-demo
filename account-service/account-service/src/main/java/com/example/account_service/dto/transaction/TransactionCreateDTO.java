package com.example.account_service.dto.transaction;

import java.sql.Timestamp;

import com.example.account_service.entity.Transaction;

import lombok.Data;

@Data
public class TransactionCreateDTO {
    private Timestamp date;
    private String type;
    private Double value;
    private Double balance; 

    public Transaction toEntity() {
        Transaction account = new Transaction();
        account.setDate(this.date);
        account.setType(this.type);
        account.setValue(this.value);
        account.setBalance(this.balance);
        return account;
    }
}
