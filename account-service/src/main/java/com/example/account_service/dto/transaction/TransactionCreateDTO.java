package com.example.account_service.dto.transaction;

import com.example.account_service.common.enums.TransactionType;
import com.example.account_service.entity.Transaction;

import lombok.Data;

@Data
public class TransactionCreateDTO {
    private String type;
    private Double value;
    private String accountNumber;

    public Transaction toEntity() {
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.valueOf(this.getType().toUpperCase()));
        transaction.setValue(this.value);
        return transaction;
    }
}
