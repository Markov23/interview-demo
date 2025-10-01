package com.example.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.user_service.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    
}
