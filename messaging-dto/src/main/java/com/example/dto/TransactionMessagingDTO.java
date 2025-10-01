package com.example.dto;

import java.time.LocalDateTime;

public record TransactionMessagingDTO(
    Long id,
    LocalDateTime date,
    String type,
    Double value,
    Double balance
) {
    
}
