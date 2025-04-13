package com.yomeekoko.tredbase_payment_system.utils.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private  Long id;
    private String name;
    private String username;
    private String role;
}
