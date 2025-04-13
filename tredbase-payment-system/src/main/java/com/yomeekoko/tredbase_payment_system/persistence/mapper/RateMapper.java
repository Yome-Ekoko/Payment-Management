package com.yomeekoko.tredbase_payment_system.persistence.mapper;

import com.yomeekoko.tredbase_payment_system.persistence.dto.RateRequest;
import com.yomeekoko.tredbase_payment_system.persistence.models.Rate;
import com.yomeekoko.tredbase_payment_system.utils.dto.RateResponse;
import org.springframework.stereotype.Component;

@Component
public class RateMapper {

    public Rate toEntity(RateRequest request) {
        Rate rate = new Rate();
        rate.setDescription(request.getDescription());
        rate.setDynmaicRate(request.getDynamicRate());
        return rate;
    }

    public RateResponse toDTO(Rate rate) {
        RateResponse response = new RateResponse();
        response.setId(rate.getId());
        response.setDescription(rate.getDescription());
        response.setDynamicRate(rate.getDynmaicRate());
        return response;
    }
}