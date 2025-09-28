package com.example.user_service.dto.client;

import lombok.Data;

@Data
public class ClientUpdateDTO {
    private String name;
    private String gender;
    private Integer age;
    private String identification;
    private String address;
    private String phoneNumber;
    private String password;
    private Boolean state;
}
