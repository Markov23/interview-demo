package com.example.account_service.dto.account;

import java.util.List;
import java.util.stream.Collectors;

import com.example.account_service.dto.transaction.TransactionDTO;
import com.example.account_service.entity.Account;
import com.example.dto.AccountMessagingDTO;

import lombok.Data;

@Data
public class AccountTransactionsDTO {
    private String number;
    private String type;
    private Double initialBalance;
    private Boolean state;
    private Integer clientId;
    private List<TransactionDTO> transactions;

    public static List<AccountMessagingDTO> fromDTOsToMessagings(List<AccountTransactionsDTO> accounts) {
        return accounts.stream().map(account -> account.toMessaging()).collect(Collectors.toList());
    }

    public AccountMessagingDTO toMessaging() {
        return new AccountMessagingDTO(
            this.number,
            this.type,
            this.initialBalance,
            this.state,
            TransactionDTO.fromDTOsToMessagings(this.transactions)
        );
    }

    public static List<AccountTransactionsDTO> fromEntities(List<Account> accounts) {
        return accounts.stream().map(account -> AccountTransactionsDTO.fromEntity(account)).collect(Collectors.toList());
    }

    public static AccountTransactionsDTO fromEntity(Account account) {
        AccountTransactionsDTO dto = new AccountTransactionsDTO();
        dto.setNumber(account.getNumber());
        dto.setType(account.getType().toString());
        dto.setInitialBalance(account.getInitialBalance());
        dto.setState(account.getState());
        dto.setClientId(account.getClientId());
        dto.setTransactions(TransactionDTO.fromEntities(account.getTransactions()));
        return dto;
    }
}
