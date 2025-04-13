package com.yomeekoko.tredbase_payment_system.service.interfaces;

import com.yomeekoko.tredbase_payment_system.persistence.dto.StudentRequest;
import com.yomeekoko.tredbase_payment_system.utils.dto.StudentResponse;

import java.util.List;

public interface StudentService {
    StudentResponse createStudent(StudentRequest request);
    StudentResponse getStudentById(String id);
    List<StudentResponse> getAllStudents();
    List<StudentResponse> getStudentsByParentId(String parentId);
}
