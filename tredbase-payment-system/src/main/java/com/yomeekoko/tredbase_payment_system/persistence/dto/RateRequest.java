package com.yomeekoko.tredbase_payment_system.persistence.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RateRequest {
    private String description;
    private Double dynamicRate;
}
