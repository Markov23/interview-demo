package com.example.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user_service.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    
}
