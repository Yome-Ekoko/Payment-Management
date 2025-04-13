package com.yomeekoko.tredbase_payment_system.utils.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
    private Long id;
    private Double balance;
    private Long parentId;
    private Long studentId;
}
