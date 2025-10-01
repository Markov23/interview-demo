package com.example.dto;

import java.time.LocalDate;

public record AccountStatementRequest(Integer clientId, LocalDate startDate, LocalDate endDate) {
}