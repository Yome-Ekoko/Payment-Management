package com.yomeekoko.tredbase_payment_system.controller;

import com.yomeekoko.tredbase_payment_system.persistence.dto.PaymentRequest;
import com.yomeekoko.tredbase_payment_system.persistence.models.Payment;
import com.yomeekoko.tredbase_payment_system.service.interfaces.PaymentService;
import com.yomeekoko.tredbase_payment_system.utils.dto.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/make-payment")
    @PreAuthorize("hasAuthority('PROCESS_ALL_PAYMENT')")

    public ResponseEntity<PaymentResponse> makePayment(@RequestBody PaymentRequest paymentRequest) {
        PaymentResponse payment = paymentService.processPayment(paymentRequest);
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPayment(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPayment(id));
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }
}