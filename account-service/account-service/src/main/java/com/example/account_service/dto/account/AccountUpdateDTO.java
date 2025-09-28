package com.example.account_service.dto.account;

import lombok.Data;

@Data
public class AccountUpdateDTO {
    private String type;
    private Double initialBalance;
    private Boolean state;
}