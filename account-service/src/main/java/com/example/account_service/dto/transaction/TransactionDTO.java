package com.example.account_service.dto.transaction;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import com.example.account_service.entity.Transaction;

import lombok.Data;

@Data
public class TransactionDTO {
    private Long id;
    private Timestamp date;
    private String type;
    private Double value;
    private Double balance; 

    public static List<TransactionDTO> fromEntities(List<Transaction> transactions) {
        return transactions.stream().map(transaction -> TransactionDTO.fromEntity(transaction)).collect(Collectors.toList());
    }

    public static TransactionDTO fromEntity(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(transaction.getId());
        dto.setDate(transaction.getDate());
        dto.setType(transaction.getType());
        dto.setValue(transaction.getValue());
        dto.setBalance(transaction.getBalance());
        return dto;
    }
}
