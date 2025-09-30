package com.example.user_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "\"CLIENT\"")
@EqualsAndHashCode(callSuper = true)
public class Client extends Person{
    @Column(name = "\"PASSWORD\"", nullable = false)
    private String password;

    @Column(name = "\"CLIENT_STATE\"", nullable = false)
    private Boolean state = true;
}
