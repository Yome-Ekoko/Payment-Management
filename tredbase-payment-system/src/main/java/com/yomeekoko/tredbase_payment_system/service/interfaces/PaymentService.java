package com.yomeekoko.tredbase_payment_system.service.interfaces;

import com.yomeekoko.tredbase_payment_system.utils.dto.PaymentResponse;

import java.util.List;

public interface PaymentService {
    PaymentResponse processPayment(Long parentId, Long studentId, Double paymentAmount) ;
    PaymentResponse getPayment(Long id);
    List<PaymentResponse> getAllPayments();
}