package com.example.user_service.dto.client;

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
    private Boolean state;

    public Client toEntity() {
        Client client = new Client();
        client.setName(this.name);
        client.setGender(this.gender);
        client.setAge(this.age);
        client.setIdentification(this.identification);
        client.setAddress(this.address);
        client.setPhoneNumber(this.phoneNumber);
        client.setPassword(this.password);
        client.setState(this.state);
        return client;
    }
}
