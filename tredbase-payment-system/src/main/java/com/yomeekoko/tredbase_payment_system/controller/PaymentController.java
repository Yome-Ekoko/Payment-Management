package com.yomeekoko.tredbase_payment_system.controller;

import com.yomeekoko.tredbase_payment_system.persistence.models.Payment;
import com.yomeekoko.tredbase_payment_system.service.interfaces.PaymentService;
import com.yomeekoko.tredbase_payment_system.utils.dto.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    @PreAuthorize("hasAuthority('READ_MY_PROFILE')")

    public ResponseEntity<PaymentResponse> makePayment(@RequestParam Long parentId,
                                               @RequestParam Long studentId,
                                               @RequestParam Double paymentAmount
                                               ) {
        PaymentResponse payment = paymentService.processPayment(parentId, studentId, paymentAmount);
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll")
    public ResponseEntity<PaymentResponse> getPayment(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPayment(id));
    }

    @GetMapping
    @PreAuthorize("permitAll")
    public ResponseEntity<List<PaymentResponse>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }
}