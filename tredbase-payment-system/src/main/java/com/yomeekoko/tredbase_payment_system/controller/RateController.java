package com.yomeekoko.tredbase_payment_system.controller;

import com.yomeekoko.tredbase_payment_system.persistence.dto.RateRequest;
import com.yomeekoko.tredbase_payment_system.service.interfaces.RateService;
import com.yomeekoko.tredbase_payment_system.utils.dto.RateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rates")
@RequiredArgsConstructor
public class RateController {

    private final RateService rateService;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADD_RATE')")
    public ResponseEntity<RateResponse> addRate(@RequestBody RateRequest dto) {
        return ResponseEntity.ok(rateService.configureRate(dto));
    }

    @GetMapping("/get")
    @PreAuthorize("permitAll")
    public ResponseEntity<List<RateResponse>> getAllRates() {
        return ResponseEntity.ok(rateService.getAllRates());
    }
}