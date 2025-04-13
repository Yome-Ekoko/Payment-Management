package com.yomeekoko.tredbase_payment_system.service.interfaces;

import com.yomeekoko.tredbase_payment_system.persistence.dto.ParentRequest;
import com.yomeekoko.tredbase_payment_system.utils.dto.ParentResponse;

import java.util.List;

public interface ParentService {
    ParentResponse createParent(ParentRequest request);
    List<ParentResponse> getParentsByStudentId(Long studentId);
}
