package com.yomeekoko.tredbase_payment_system.service.interfaces.auth;

import com.yomeekoko.tredbase_payment_system.persistence.dto.RegisterUserRequest;
import com.yomeekoko.tredbase_payment_system.persistence.models.auth.Users;

import java.util.Optional;

public interface UserService {

    Optional<Users> findOneByUsername(String username);
    Users registerOneAdmin(RegisterUserRequest request);

}
