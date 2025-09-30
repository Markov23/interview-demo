package com.example.dto;

public record ClientValidationResponse(String requestId, Integer clientId, boolean exists) {
    
}
