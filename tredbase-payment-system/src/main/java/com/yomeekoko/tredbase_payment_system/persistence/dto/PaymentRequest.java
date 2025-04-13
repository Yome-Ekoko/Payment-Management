package com.yomeekoko.tredbase_payment_system.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentRequest {
    private Long parentId;
    private Long studentId;
    private Double paymentAmount;
}
