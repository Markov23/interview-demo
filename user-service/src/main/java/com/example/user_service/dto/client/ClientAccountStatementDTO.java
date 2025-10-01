package com.example.user_service.dto.client;

import java.time.LocalDateTime;
import java.util.List;

import com.example.dto.AccountMessagingDTO;
import com.example.user_service.entity.Client;

import lombok.Data;

@Data
public class ClientAccountStatementDTO {
    private Integer id;
    private String name;
    private String identification;
    private Boolean state;
    private LocalDateTime date;
    private List<AccountMessagingDTO> accounts;

    public static ClientAccountStatementDTO fromEntity(Client client) {
        ClientAccountStatementDTO dto = new ClientAccountStatementDTO();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setIdentification(client.getIdentification());
        dto.setState(client.getState());
        return dto;
    }
}
