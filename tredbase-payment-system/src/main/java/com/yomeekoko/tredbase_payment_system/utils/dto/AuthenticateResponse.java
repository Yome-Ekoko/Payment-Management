package com.yomeekoko.tredbase_payment_system.utils.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class AuthenticateResponse extends Response {
    private String token;

    public AuthenticateResponse(String message, int code, HttpStatus status, LocalDateTime timestamp, String token) {
        super(message, code, status, timestamp);
        this.token = token;
    }
}
