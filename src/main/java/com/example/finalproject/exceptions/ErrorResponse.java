package com.example.finalproject.exceptions;

import jakarta.persistence.EntityNotFoundException;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ErrorResponse extends EntityNotFoundException {

    private String code;
    private String message;
    private HttpStatus httpStatus;
}
