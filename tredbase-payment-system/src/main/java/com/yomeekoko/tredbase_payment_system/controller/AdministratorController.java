package com.yomeekoko.tredbase_payment_system.controller;

import com.yomeekoko.tredbase_payment_system.persistence.dto.RegisterUserRequest;
import com.yomeekoko.tredbase_payment_system.service.interfaces.auth.AuthenticationService;
import com.yomeekoko.tredbase_payment_system.utils.dto.RegisterResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/administrator")
@RequiredArgsConstructor
//@Tag(name =" Administrators", description = "Register administrators")
public class AdministratorController {
    private final AuthenticationService authenticationService;
    @Operation(summary = "Register one administrator")
    @ApiResponse(responseCode = "201", description = "Administrator created", content = {@Content(mediaType = "application/json")})
    //@PreAuthorize("hasAuthority('ADD_ADMINISTRATORS')")
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerOneAdmin(@Valid @RequestBody RegisterUserRequest request) {
        RegisterResponse response = authenticationService.registerOneAdmin(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
