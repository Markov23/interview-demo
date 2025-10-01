package com.example.account_service.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.account_service.common.enums.AccountType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "\"ACCOUNT\"")
public class Account {
    @Id
    @Column(name = "\"ACCOUNT_NUMBER\"")
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(name = "\"ACCOUNT_TYPE\"")
    private AccountType type;

    @Column(name = "\"INITIAL_BALANCE\"", nullable = false)
    private Double initialBalance;

    @Column(name = "\"ACCOUNT_STATE\"", nullable = false)
    private Boolean state = true;

    @Column(name = "\"CLIENT_ID\"", nullable = false)
    private Integer clientId;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Transaction> transactions = new ArrayList<Transaction>();
}
