package com.yomeekoko.tredbase_payment_system.persistence.dto;

import lombok.Data;

import java.util.List;

@Data
public class ParentRequest {
    private String name;
    private String email;
    private List<Long> studentIds; // List of student IDs associated with the parent

}
