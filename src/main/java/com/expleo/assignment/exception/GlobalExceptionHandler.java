package com.expleo.assignment.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.expleo.assignment.dto.BaseResponseDTO;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<BaseResponseDTO<Void>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        BaseResponseDTO<Void> response = new BaseResponseDTO<>(false, "Invalid input: " + ex.getValue(), null);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<BaseResponseDTO<Void>> handleResourceNotFound(NoResourceFoundException ex) {
        BaseResponseDTO<Void> response = new BaseResponseDTO<>(false, "Resource not found: " + ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponseDTO<Void>> handleGenericException(Exception ex) {
        BaseResponseDTO<Void> response = new BaseResponseDTO<>(false, "An error occurred: " + ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
