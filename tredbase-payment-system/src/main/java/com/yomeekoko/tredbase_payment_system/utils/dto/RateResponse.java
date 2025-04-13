package com.yomeekoko.tredbase_payment_system.utils.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RateResponse {
    private Long id;
    private String description;
    private Double dynamicRate;
}
