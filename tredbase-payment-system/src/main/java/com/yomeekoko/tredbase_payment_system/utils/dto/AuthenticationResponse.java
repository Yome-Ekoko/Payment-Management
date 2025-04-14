

package com.yomeekoko.tredbase_payment_system.utils.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

    @EqualsAndHashCode(callSuper = true)
    @Data
    @RequiredArgsConstructor
    public class AuthenticationResponse extends Response {
        private String token;

        public AuthenticationResponse(String message, int code, HttpStatus status, LocalDateTime timestamp, String token) {
            super(message, code, status, timestamp);

            this.token = token;
        }
        public AuthenticationResponse(String token) {
            super();
            this.token = token;
        }
    }


