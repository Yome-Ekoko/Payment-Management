package com.yomeekoko.tredbase_payment_system.controller;

import com.yomeekoko.tredbase_payment_system.persistence.dto.StudentRequest;
import com.yomeekoko.tredbase_payment_system.service.interfaces.StudentService;
import com.yomeekoko.tredbase_payment_system.utils.dto.StudentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity<StudentResponse> addStudent(@RequestBody StudentRequest dto) {
        return ResponseEntity.ok(studentService.createStudent(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable String id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/by-parent/{parentId}")
    public ResponseEntity<List<StudentResponse>> getStudentsByParentId(@PathVariable String parentId) {
        return ResponseEntity.ok(studentService.getStudentsByParentId(parentId));
    }
}