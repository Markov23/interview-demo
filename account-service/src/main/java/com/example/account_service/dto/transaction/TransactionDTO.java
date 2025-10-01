package com.example.account_service.dto.transaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.example.account_service.entity.Transaction;
import com.example.dto.TransactionMessagingDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionDTO {
    private Long id;
    private LocalDateTime date;
    private String type;
    private Double value;
    private Double balance; 
    private String accountNumber;

    public static List<TransactionMessagingDTO> fromDTOsToMessagings(List<TransactionDTO> transactions) {
        return transactions.stream().map(transaction -> transaction.toMessaging()).collect(Collectors.toList());
    }

    public TransactionMessagingDTO toMessaging() {
        return new TransactionMessagingDTO(
            this.id,
            this.date,
            this.type,
            this.value,
            this.balance
        );
    }

    public static List<TransactionDTO> fromEntities(List<Transaction> transactions) {
        return transactions.stream().map(transaction -> TransactionDTO.fromEntity(transaction)).collect(Collectors.toList());
    }

    public static TransactionDTO fromEntity(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(transaction.getId());
        dto.setDate(transaction.getDate());
        dto.setType(transaction.getType().toString());
        dto.setValue(transaction.getValue());
        dto.setBalance(transaction.getBalance());
        dto.setAccountNumber(transaction.getAccount().getNumber());
        return dto;
    }
}
