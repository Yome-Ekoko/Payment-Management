package com.yomeekoko.tredbase_payment_system.utils.dto;

import com.yomeekoko.tredbase_payment_system.persistence.models.Parent;
import lombok.Data;

import java.util.List;

@Data
public class StudentResponse {
    private Long id;
    private String name;
    private String email;
    private List<Long> parentIds;
}
