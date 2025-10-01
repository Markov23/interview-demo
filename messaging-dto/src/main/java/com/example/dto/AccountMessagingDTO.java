package com.example.dto;

import java.util.List;

public record AccountMessagingDTO(
    String accountNumber,
    String type,
    Double balance,
    Boolean state,
    List<TransactionMessagingDTO> transactions
) {
    
}
