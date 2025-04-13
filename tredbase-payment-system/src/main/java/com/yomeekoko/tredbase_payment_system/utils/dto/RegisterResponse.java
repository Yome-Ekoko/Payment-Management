package com.yomeekoko.tredbase_payment_system.utils.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private  Long id;
    private String username;
    private String name;
    private String role;
    private String jwt;
}
