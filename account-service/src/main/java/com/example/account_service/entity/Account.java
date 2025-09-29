package com.example.account_service.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    private Long number;

    @Column(name = "\"ACCOUNT_TYPE\"")
    private String type;

    @Column(name = "\"INITIAL_BALANCE\"")
    private Double initialBalance;

    @Column(name = "\"ACCOUNT_STATE\"")
    private Boolean state;

    @Column(name = "\"CLIENT_ID\"")
    private Integer clientId;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<Transaction>();
}
