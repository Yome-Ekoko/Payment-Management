package com.yomeekoko.tredbase_payment_system.service.interfaces;

import com.yomeekoko.tredbase_payment_system.persistence.dto.PaymentRequest;
import com.yomeekoko.tredbase_payment_system.utils.dto.PaymentResponse;

import java.util.List;

public interface PaymentService {
    PaymentResponse processPayment(PaymentRequest paymentRequest) ;
    PaymentResponse getPayment(Long id);
    List<PaymentResponse> getAllPayments();
}