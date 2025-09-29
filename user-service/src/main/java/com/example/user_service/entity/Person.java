package com.example.user_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"ID\"")
    private Integer id;

    @Column(name = "\"NAME\"")
    private String name;

    @Column(name = "\"GENDER\"")
    private String gender;

    @Column(name = "\"AGE\"")
    private Integer age;

    @Column(name = "\"IDENTIFICATION\"")
    private String identification;

    @Column(name = "\"ADDRESS\"")
    private String address;

    @Column(name = "\"PHONE_NUMBER\"")
    private String phoneNumber;
}
