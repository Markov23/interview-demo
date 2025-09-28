package com.example.account_service.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TRANSACTION")
public class Transaction {
    @Id
    private Long id;
    private Timestamp date;
    private String type;
    private Double value;
    private Double balance; 
}
