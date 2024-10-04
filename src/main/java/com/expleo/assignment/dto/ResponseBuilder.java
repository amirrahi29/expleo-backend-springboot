package com.expleo.assignment.dto;

import org.springframework.http.ResponseEntity;

public class ResponseBuilder {
    public static <T> ResponseEntity<BaseResponseDTO<T>> buildResponse(boolean success, String message, T data) {
        return ResponseEntity.ok(new BaseResponseDTO<>(success, message, data));
    }
    public static <T> ResponseEntity<BaseResponseDTO<T>> buildResponse(boolean success, String message, T data, int status) {
        return ResponseEntity.status(status).body(new BaseResponseDTO<>(success, message, data));
    }
}
