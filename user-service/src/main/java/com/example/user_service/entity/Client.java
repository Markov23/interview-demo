package com.example.user_service.entity;

import com.example.user_service.common.enums.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "\"CLIENT\"")
@EqualsAndHashCode(callSuper = true)
public class Client extends Person{
    @Column(name = "\"PASSWORD\"", nullable = false)
    private String password;

    @Column(name = "\"CLIENT_STATE\"", nullable = false)
    private Boolean state = true;

    public Client(
        Integer id,
        String name,
        Gender gender,
        Integer age,
        String identification,
        String address,
        String phoneNumber,
        String password,
        Boolean state
    ) {
        super(
            id,
            name,
            gender,
            age,
            identification,
            address,
            phoneNumber
        );
        this.password = password;
        this.state = state;
    }

    public Client(
        String name,
        Gender gender,
        Integer age,
        String identification,
        String address,
        String phoneNumber,
        String password,
        Boolean state
    ) {
        super(
            name,
            gender,
            age,
            identification,
            address,
            phoneNumber
        );
        this.password = password;
        this.state = state;
    }
}
