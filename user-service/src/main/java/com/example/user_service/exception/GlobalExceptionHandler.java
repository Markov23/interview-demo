package com.example.user_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.user_service.dto.ResponseApi;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseApi<?>> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseApi<>(ex.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseApi<?>> handleBadRequest(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseApi<>(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseApi<?>> handleAll(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseApi<>("Something went wrong, try later"));
    }
}
