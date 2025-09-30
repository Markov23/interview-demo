package com.example.user_service.dto.client;

import com.example.user_service.common.enums.Gender;
import com.example.user_service.entity.Client;

import lombok.Data;

@Data
public class ClientCreateDTO {
    private String name;
    private String gender;
    private Integer age;
    private String identification;
    private String address;
    private String phoneNumber;
    private String password;

    public Client toEntity() {
        Client client = new Client();
        client.setName(this.name);
        client.setGender(Gender.valueOf(this.getGender().toUpperCase()));
        client.setAge(this.age);
        client.setIdentification(this.identification);
        client.setAddress(this.address);
        client.setPhoneNumber(this.phoneNumber);
        return client;
    }
}
