package com.yomeekoko.tredbase_payment_system.controller;

import com.yomeekoko.tredbase_payment_system.service.interfaces.AccountService;
import com.yomeekoko.tredbase_payment_system.utils.dto.AccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/create/parent/{parentId}")
    public ResponseEntity<AccountResponse> createAccountForParent(@PathVariable Long parentId) {
        return ResponseEntity.ok(accountService.createAccountForParent(parentId));
    }

    @PostMapping("/create/student/{studentId}")

    public ResponseEntity<AccountResponse> createAccountForStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(accountService.createAccountForStudent(studentId));
    }

    @GetMapping("/parent/{parentId}")
    public ResponseEntity<AccountResponse> getAccountForParent(@PathVariable Long parentId) {
        return ResponseEntity.ok(accountService.getAccountForParent(parentId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<AccountResponse> getAccountForStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(accountService.getAccountForStudent(studentId));
    }

    @GetMapping("/balance/parent/{parentId}")
    public ResponseEntity<Double> getBalanceForParent(@PathVariable Long parentId) {
        return ResponseEntity.ok(accountService.getAccountBalanceForParent(parentId));
    }

    @GetMapping("/balance/student/{studentId}")
    public ResponseEntity<Double> getBalanceForStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(accountService.getAccountBalanceForStudent(studentId));
    }
}
