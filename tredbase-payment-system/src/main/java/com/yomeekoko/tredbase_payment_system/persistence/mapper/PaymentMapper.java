package com.yomeekoko.tredbase_payment_system.persistence.mapper;

import com.yomeekoko.tredbase_payment_system.persistence.models.Payment;
import com.yomeekoko.tredbase_payment_system.utils.dto.PaymentResponse;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentResponse toDTO(Payment payment) {
        PaymentResponse response = new PaymentResponse();
        //response.setId(payment.getId());
        response.setParentId(payment.getParent().getId());
        response.setStudentId(payment.getStudent().getId());
        response.setAmount(payment.getAmount());
        response.setDate(payment.getDate());
        response.setDynamicRate(payment.getDynamicRate());
        return response;
    }
}