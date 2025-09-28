package com.example.account_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ACCOUNT")
public class Account {
    @Id
    private Long number;
    private String type;
    private Double initialBalance;
    private Boolean state;
}
