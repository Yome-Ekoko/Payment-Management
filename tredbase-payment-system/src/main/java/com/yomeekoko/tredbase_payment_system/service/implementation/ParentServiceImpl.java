package com.yomeekoko.tredbase_payment_system.service.implementation;

import com.yomeekoko.tredbase_payment_system.persistence.dto.ParentRequest;
import com.yomeekoko.tredbase_payment_system.persistence.mapper.ParentMapper;
import com.yomeekoko.tredbase_payment_system.persistence.models.Parent;
import com.yomeekoko.tredbase_payment_system.persistence.models.Student;
import com.yomeekoko.tredbase_payment_system.persistence.repository.ParentRepository;
import com.yomeekoko.tredbase_payment_system.persistence.repository.StudentRepository;
import com.yomeekoko.tredbase_payment_system.service.interfaces.ParentService;
import com.yomeekoko.tredbase_payment_system.utils.dto.ParentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl  implements ParentService {
    private final ParentRepository parentRepository;
    private final StudentRepository studentRepository;
    private final ParentMapper parentMapper;

    @Override
    public ParentResponse createParent(ParentRequest request) {
        // Validate student IDs before creating the parent
        if (request.getStudentIds() == null || request.getStudentIds().isEmpty()) {
            throw new IllegalArgumentException("Student IDs must be provided.");
        }

        List<Student> students = studentRepository.findAllById(request.getStudentIds());

        // Check if all provided student IDs exist
        if (students.size() != request.getStudentIds().size()) {
            throw new IllegalArgumentException("One or more students do not exist.");
        }

        // Convert the ParentRequest to a Parent entity
        Parent parent = parentMapper.toEntity(request);

        // Save the parent to the database
        parent = parentRepository.save(parent);

        for (Student student : students) {
            // Add parent to student's list of parents
            student.getParents().add(parent);

            // Add student to parent's list of students
            parent.getStudents().add(student);

            // Save the updated student
            studentRepository.save(student);
        }

        // Save the updated parent to ensure the relationship is persisted
        parent = parentRepository.save(parent);

        // Return the saved parent as a DTO
        return parentMapper.toDTO(parent);
    }

    @Override
    public List<ParentResponse> getParentsByStudentId(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return student.getParents()
                .stream()
                .map(parentMapper::toDTO)
                .collect(Collectors.toList());
    }
}
