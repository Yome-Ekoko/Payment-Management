package com.yomeekoko.tredbase_payment_system.persistence.mapper;

import com.yomeekoko.tredbase_payment_system.persistence.dto.ParentRequest;
import com.yomeekoko.tredbase_payment_system.persistence.models.Parent;
import com.yomeekoko.tredbase_payment_system.persistence.models.Student;
import com.yomeekoko.tredbase_payment_system.persistence.repository.StudentRepository;
import com.yomeekoko.tredbase_payment_system.utils.dto.ParentResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParentMapper {

    private final StudentRepository studentRepository;

    // Constructor injection for StudentRepository
    public ParentMapper(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Parent toEntity(ParentRequest request) {
        Parent parent = new Parent();
        parent.setName(request.getName());
        parent.setEmail(request.getEmail());

        // If the request contains studentIds, fetch students from the database and link them
        if (request.getStudentIds() != null && !request.getStudentIds().isEmpty()) {
            List<Student> students = studentRepository.findAllById(request.getStudentIds());
            parent.setStudents(new HashSet<>(students)); // Convert List to Set
        }

        return parent;
    }

    public ParentResponse toDTO(Parent parent) {
        ParentResponse response = new ParentResponse();
        response.setId(parent.getId());
        response.setName(parent.getName());
        response.setEmail(parent.getEmail());

        // Fix: include the student IDs only if the list is not null
        if (parent.getStudents() != null && !parent.getStudents().isEmpty()) {
            response.setStudentIds(
                    parent.getStudents().stream()
                            .map(Student::getId)  // Extract the student IDs
                            .collect(Collectors.toList())
            );
        } else {
            response.setStudentIds(new ArrayList<>());  // Return an empty list if no students
        }

        return response;
    }
}