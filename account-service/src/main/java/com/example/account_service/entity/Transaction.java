package com.example.account_service.entity;

import java.time.LocalDateTime;

import com.example.account_service.common.enums.TransactionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "\"TRANSACTION\"")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"TRANSACTION_ID\"")
    private Long id;
    
    @Column(name = "\"TRANSACTION_DATE\"", nullable = false)
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(name = "\"TRANSACTION_TYPE\"")
    private TransactionType type;

    @Column(name = "\"VALUE\"", nullable = false)
    private Double value;

    @Column(name = "\"BALANCE\"", nullable = false)
    private Double balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"ACCOUNT_NUMBER\"")
    private Account account;
}
