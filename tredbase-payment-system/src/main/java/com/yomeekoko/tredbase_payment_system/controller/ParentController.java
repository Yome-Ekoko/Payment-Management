package com.yomeekoko.tredbase_payment_system.controller;

import com.yomeekoko.tredbase_payment_system.persistence.dto.ParentRequest;
import com.yomeekoko.tredbase_payment_system.service.interfaces.ParentService;
import com.yomeekoko.tredbase_payment_system.utils.dto.ParentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parents")
@RequiredArgsConstructor
public class ParentController {

    private final ParentService parentService;

    @PostMapping
    @PreAuthorize("permitAll")
    public ResponseEntity<ParentResponse> addParent(@RequestBody ParentRequest dto) {
        return ResponseEntity.ok(parentService.createParent(dto));
    }

    @GetMapping("/{studentId}/by-student")
    @PreAuthorize("permitAll")
    public ResponseEntity<List<ParentResponse>> getParentsByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(parentService.getParentsByStudentId(studentId));
    }
    }
