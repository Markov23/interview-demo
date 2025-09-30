package com.example.account_service.dto;

import lombok.Data;

@Data
public class ResponseApi<T> {
    private String message;
    private T data;

    public ResponseApi() {}

    public ResponseApi(String message) {
        this.message = message;
    }

    public ResponseApi(String message, T data) {
        this.message = message;
        this.data = data;
    }
}
