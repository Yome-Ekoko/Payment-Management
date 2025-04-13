package com.yomeekoko.tredbase_payment_system.persistence.repository;

import com.yomeekoko.tredbase_payment_system.persistence.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}