package com.yomeekoko.tredbase_payment_system.utils.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class PaymentResponse {
    private Long parentId;     // Parent ID
    private Long studentId;    // Student ID
    private Double dynamicRate;       // Rate ID associated with the payment (dynamic rate)
    private Double amount;     // The amount being paid
    private String description;
    private LocalDateTime date; // Payment date

}
