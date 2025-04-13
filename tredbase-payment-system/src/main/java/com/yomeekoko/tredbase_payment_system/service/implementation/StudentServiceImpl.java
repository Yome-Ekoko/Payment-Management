package com.yomeekoko.tredbase_payment_system.service.implementation;

import com.yomeekoko.tredbase_payment_system.persistence.dto.StudentRequest;
import com.yomeekoko.tredbase_payment_system.persistence.mapper.StudentMapper;
import com.yomeekoko.tredbase_payment_system.persistence.models.Parent;
import com.yomeekoko.tredbase_payment_system.persistence.models.Student;
import com.yomeekoko.tredbase_payment_system.persistence.repository.ParentRepository;
import com.yomeekoko.tredbase_payment_system.persistence.repository.StudentRepository;
import com.yomeekoko.tredbase_payment_system.service.interfaces.StudentService;
import com.yomeekoko.tredbase_payment_system.utils.dto.StudentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ParentRepository parentRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentResponse createStudent(StudentRequest request) {
        // Map the request to a student entity (without associating any parents)
        Student student = studentMapper.toEntity(request);

        // Save the student without associating parents
        student = studentRepository.save(student);

        // Return the student DTO
        return studentMapper.toDTO(student);
    }



    @Override
    public StudentResponse getStudentById(String id) {
        Long studentId = Long.parseLong(id); // Convert String to Long
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return studentMapper.toDTO(student);
    }

    @Override
    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentResponse> getStudentsByParentId(String parentId) {
        Long parentLongId = Long.parseLong(parentId); // Convert String to Long
        Parent parent = parentRepository.findById(parentLongId)
                .orElseThrow(() -> new RuntimeException("Parent not found"));

        return parent.getStudents()
                .stream()
                .map(studentMapper::toDTO)
                .collect(Collectors.toList());
    }
}