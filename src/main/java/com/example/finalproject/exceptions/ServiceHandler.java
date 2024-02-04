package com.example.finalproject.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ServiceHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> notFound(EntityNotFoundException ex) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .code("Ошибка при выполнении операции (-1)")
                .message("Пользователь не найден").build(), HttpStatus.OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> insufficientFunds(IllegalArgumentException ex) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .code("Недостаточно средств (0)")
                .message(ex.getMessage()).build(), HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> errorAddBalance(Exception ex) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .code("Ошибка при выполнении операции (0)")
                .message(ex.getMessage()).build(), HttpStatus.OK);
    }
}
