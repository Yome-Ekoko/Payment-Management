package com.yomeekoko.tredbase_payment_system.persistence.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentRequest {
    private String name;
    private String email;
    //private List<Long> parentIds;
}
