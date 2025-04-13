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
        // Convert the ParentRequest to a Parent entity
        Parent parent = parentMapper.toEntity(request);

        // Save the parent to the database
        parent = parentRepository.save(parent);

        if (request.getStudentIds() != null && !request.getStudentIds().isEmpty()) {
            List<Student> students = studentRepository.findAllById(request.getStudentIds());

            for (Student student : students) {
                student.getParents().add(parent);  // Add parent to student's list of parents
                studentRepository.save(student);   // Save the updated student
            }
        }

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
