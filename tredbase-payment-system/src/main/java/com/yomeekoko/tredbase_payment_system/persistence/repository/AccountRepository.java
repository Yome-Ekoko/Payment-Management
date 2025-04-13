package com.yomeekoko.tredbase_payment_system.persistence.repository;


import com.yomeekoko.tredbase_payment_system.persistence.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByParentId(Long parentId);  // Retrieve account by parent ID
    Optional<Account> findByStudentId(Long studentId);
}
