package com.example.user_service.dto.client;

import java.util.List;
import java.util.stream.Collectors;

import com.example.user_service.entity.Client;

import lombok.Data;

@Data
public class ClientDTO {
    private Integer id;
    private String name;
    private String gender;
    private Integer age;
    private String identification;
    private String address;
    private String phoneNumber;
    private Boolean state;

    public static List<ClientDTO> fromEntities(List<Client> clients) {
        return clients.stream().map(client -> ClientDTO.fromEntity(client)).collect(Collectors.toList());
    }

    public static ClientDTO fromEntity(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setGender(client.getGender().toString());
        dto.setAge(client.getAge());
        dto.setIdentification(client.getIdentification());
        dto.setAddress(client.getAddress());
        dto.setPhoneNumber(client.getPhoneNumber());
        dto.setState(client.getState());
        return dto;
    }
}
