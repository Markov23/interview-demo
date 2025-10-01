package com.example.account_service.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.account_service.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    @Query("SELECT c FROM Account c LEFT JOIN FETCH c.transactions t WHERE c.clientId = :clientId AND (t.date BETWEEN :startDate AND :endDate)")
    List<Account> findByClientIdWithTransactions(@Param("clientId") Integer clientId,
            @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
