package com.yomeekoko.tredbase_payment_system.persistence.repository;

import com.yomeekoko.tredbase_payment_system.persistence.models.auth.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(String jwt);
}
