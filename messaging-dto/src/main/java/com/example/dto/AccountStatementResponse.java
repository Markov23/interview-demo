package com.example.dto;

import java.time.LocalDateTime;
import java.util.List;

public record AccountStatementResponse(
    LocalDateTime requestDate,
    List<AccountMessagingDTO> accounts
) {
}
