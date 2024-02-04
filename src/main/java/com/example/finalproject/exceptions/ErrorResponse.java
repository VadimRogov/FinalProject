package com.example.finalproject.exceptions;

import jakarta.persistence.EntityNotFoundException;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ErrorResponse {

    private String code;
    private String message;
}
