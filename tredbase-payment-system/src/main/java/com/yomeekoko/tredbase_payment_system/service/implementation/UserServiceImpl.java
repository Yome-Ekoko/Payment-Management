package com.yomeekoko.tredbase_payment_system.service.implementation;


import com.yomeekoko.tredbase_payment_system.exception.InvalidPasswordException;
import com.yomeekoko.tredbase_payment_system.persistence.dto.RegisterUserRequest;
import com.yomeekoko.tredbase_payment_system.persistence.models.auth.Users;
import com.yomeekoko.tredbase_payment_system.persistence.repository.UserRepository;
import com.yomeekoko.tredbase_payment_system.service.interfaces.auth.UserService;
import com.yomeekoko.tredbase_payment_system.utils.constants.Message;
import com.yomeekoko.tredbase_payment_system.utils.enums.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public Optional<Users> findOneByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Users registerOneAdmin(RegisterUserRequest request) {
        validatePassword(request);
        Users users = new Users();
        users.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        users.setUsername(request.getUsername());
        users.setName(request.getName());
        users.setRole(RoleEnum.ROLE_ADMIN);
        return userRepository.save(users);
    }

    private void validatePassword(RegisterUserRequest request) {

        if(!StringUtils.hasText(request.getPassword()) || !StringUtils.hasText(request.getRepeatedPassword())){
            throw new InvalidPasswordException(Message.PASSWORD_NOT_VALID,400, HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
        if(!request.getPassword().equals(request.getRepeatedPassword())){
            throw new InvalidPasswordException(Message.PASSWORD_NOT_VALID,400, HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
    }}
