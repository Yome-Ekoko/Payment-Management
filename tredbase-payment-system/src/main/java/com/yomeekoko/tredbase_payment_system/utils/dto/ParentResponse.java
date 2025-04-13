package com.yomeekoko.tredbase_payment_system.utils.dto;

import lombok.Data;

import java.util.List;

@Data
public class ParentResponse {
    private Long id;
    private String name;
    private String email;
    private List<Long> studentIds;

}
