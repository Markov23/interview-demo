package com.example.user_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "CLIENT")
@EqualsAndHashCode(callSuper = true)
public class Client extends Person{
    private String password;
    private Boolean state;
}
