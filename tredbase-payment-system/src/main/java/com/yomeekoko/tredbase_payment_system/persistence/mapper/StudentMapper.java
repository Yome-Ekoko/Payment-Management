package com.yomeekoko.tredbase_payment_system.persistence.mapper;

import com.yomeekoko.tredbase_payment_system.persistence.dto.StudentRequest;
import com.yomeekoko.tredbase_payment_system.persistence.models.Parent;
import com.yomeekoko.tredbase_payment_system.persistence.models.Student;
import com.yomeekoko.tredbase_payment_system.utils.dto.StudentResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentMapper {

    public Student toEntity(StudentRequest request) {
        Student student = new Student();
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        return student;
    }

    public StudentResponse toDTO(Student student) {
        StudentResponse response = new StudentResponse();
        response.setId(student.getId());
        response.setName(student.getName());
        response.setEmail(student.getEmail());

        // Map the parent IDs correctly
        response.setParentIds(
                student.getParents() != null
                        ? student.getParents().stream()
                        .map(Parent::getId)
                        .collect(Collectors.toList())
                        : new ArrayList<>()
        );
        return response;
    }
}