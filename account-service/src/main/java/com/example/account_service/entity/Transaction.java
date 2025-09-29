package com.example.account_service.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "\"TRANSACTION\"")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"TRANSACTION_ID\"")
    private Long id;
    
    @Column(name = "\"TRANSACTION_DATE\"")
    private Timestamp date;

    @Column(name = "\"TRANSACTION_TYPE\"")
    private String type;

    @Column(name = "\"VALUE\"")
    private Double value;

    @Column(name = "\"BALANCE\"")
    private Double balance; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"ACCOUNT_NUMBER\"")
    private Account account;
}
