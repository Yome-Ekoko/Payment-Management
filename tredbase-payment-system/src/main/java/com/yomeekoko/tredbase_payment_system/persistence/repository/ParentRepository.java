package com.yomeekoko.tredbase_payment_system.persistence.repository;

import com.yomeekoko.tredbase_payment_system.persistence.models.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
}
