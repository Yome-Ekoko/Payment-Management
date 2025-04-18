package com.yomeekoko.tredbase_payment_system.exception;

import com.yomeekoko.tredbase_payment_system.utils.dto.ApiErrorResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@NoArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleConstraintViolationException(ConstraintViolationException e) {
        List<String> errorMessages = e.getConstraintViolations()
                .stream()
                .map(violation -> String.format("%s: %s", violation.getPropertyPath(), violation.getMessage()))
                .collect(Collectors.toList());
        String errorMessage = "Validation failed for the following fields:\n  " +
                String.join("\n", errorMessages);
        ApiErrorResponse apiError = new ApiErrorResponse(errorMessage, 400, HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleParentNotFoundException(ResourceNotFoundException e) {
        ApiErrorResponse apiError = new ApiErrorResponse(e.getMessage(), 404, e.getStatus(), e.getTimestamp());
        return ResponseEntity.status(e.getStatus()).body(apiError);

    }
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleStudentNotFoundException(CategoryNotFoundException e) {
        ApiErrorResponse apiError = new ApiErrorResponse(e.getMessage(), 404, e.getStatus(), e.getTimestamp());
        return ResponseEntity.status(e.getStatus()).body(apiError);
    }
}
