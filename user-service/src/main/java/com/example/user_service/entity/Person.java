package com.example.user_service.entity;

import com.example.user_service.common.enums.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@MappedSuperclass
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"ID\"")
    private Integer id;

    @Column(name = "\"NAME\"", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "\"GENDER\"")
    private Gender gender;

    @Column(name = "\"AGE\"", nullable = false)
    private Integer age;

    @Column(name = "\"IDENTIFICATION\"", nullable = false)
    private String identification;

    @Column(name = "\"ADDRESS\"", nullable = false)
    private String address;

    @Column(name = "\"PHONE_NUMBER\"", nullable = false)
    private String phoneNumber;

    public Person(
        String name,
        Gender gender,
        Integer age,
        String identification,
        String address,
        String phoneNumber
    ) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.identification = identification;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
