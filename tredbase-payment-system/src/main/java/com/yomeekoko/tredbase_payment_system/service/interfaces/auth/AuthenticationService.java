package com.yomeekoko.tredbase_payment_system.service.interfaces.auth;

import com.yomeekoko.tredbase_payment_system.persistence.dto.AuthenticateRequest;
import com.yomeekoko.tredbase_payment_system.persistence.dto.RegisterUserRequest;
import com.yomeekoko.tredbase_payment_system.utils.dto.AuthenticateResponse;
import com.yomeekoko.tredbase_payment_system.utils.dto.RegisterResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthenticationService {
    RegisterResponse registerOneAdmin(RegisterUserRequest request);

   // AuthenticateResponse login(AuthenticateRequest request);

    boolean validateToken(String token);

    //UserDto findLoggedInUser();

    void logout(HttpServletRequest request);
}
