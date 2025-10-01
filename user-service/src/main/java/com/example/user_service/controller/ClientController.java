package com.example.user_service.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.user_service.common.constants.ApiConstants;
import com.example.user_service.dto.ResponseApi;
import com.example.user_service.dto.client.ClientAccountStatementDTO;
import com.example.user_service.dto.client.ClientCreateDTO;
import com.example.user_service.dto.client.ClientDTO;
import com.example.user_service.dto.client.ClientUpdateDTO;
import com.example.user_service.service.ClientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@Tag(name = "Clients")
@RequestMapping(ApiConstants.API_CLIENT_URL)
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(summary = "Create a new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client created"),
            @ApiResponse(responseCode = "400", description = "Invalid client data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<ResponseApi<ClientDTO>> createClient(@RequestBody ClientCreateDTO clientCreateDTO) {
        ClientDTO client = clientService.createClient(clientCreateDTO);

        ResponseApi<ClientDTO> response = new ResponseApi<>("Client created successfully", client);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get a list of all clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clients list"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<ResponseApi<List<ClientDTO>>> getAllClients() {
        ResponseApi<List<ClientDTO>> response = new ResponseApi<>("Clients retrieved successfully",
                clientService.getAllClients());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get a client by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client information"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseApi<ClientDTO>> getClientById(@PathVariable Integer id) {
        ClientDTO client = clientService.getClientById(id);

        ResponseApi<ClientDTO> response = new ResponseApi<>("Client retrieved successfully", client);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get client account statement by client id and date interval")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account statement"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/accounts/{id}")
    public ResponseEntity<ResponseApi<ClientAccountStatementDTO>> getAccountStatementByClientId(
            @PathVariable Integer id,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        ClientAccountStatementDTO account = clientService.getAccountStatementByClientId(id, startDate, endDate);

        ResponseApi<ClientAccountStatementDTO> response = new ResponseApi<>(
            "Account statement retrieved successfully",account
        );
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update a client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client updated"),
            @ApiResponse(responseCode = "400", description = "Invalid client data"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResponseApi<ClientDTO>> updateClient(@PathVariable Integer id,
            @RequestBody ClientUpdateDTO clientUpdateDTO) {
        ClientDTO client = clientService.updateClient(id, clientUpdateDTO);

        ResponseApi<ClientDTO> response = new ResponseApi<>("Client successfully update", client);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete a client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client deleted"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApi<Integer>> deleteClient(@PathVariable Integer id) {
        Integer idDeleted = clientService.deleteClient(id);

        ResponseApi<Integer> response = new ResponseApi<>("Client successfully deleted", idDeleted);
        return ResponseEntity.ok(response);
    }
}
