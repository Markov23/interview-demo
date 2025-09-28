package com.example.account_service.dto.transaction;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class TransactionUpdateDTO {
    private Timestamp date;
    private String type;
    private Double value;
    private Double balance; 
}
