package com.yomeekoko.tredbase_payment_system.service.interfaces;

import com.yomeekoko.tredbase_payment_system.persistence.dto.RateRequest;
import com.yomeekoko.tredbase_payment_system.utils.dto.RateResponse;

import java.util.List;

public interface RateService {
    RateResponse configureRate(RateRequest request);
    List<RateResponse> getAllRates();
    Double getCurrentRate();


}
