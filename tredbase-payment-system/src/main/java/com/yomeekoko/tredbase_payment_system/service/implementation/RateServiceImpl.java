package com.yomeekoko.tredbase_payment_system.service.implementation;

import com.yomeekoko.tredbase_payment_system.persistence.dto.RateRequest;
import com.yomeekoko.tredbase_payment_system.persistence.mapper.RateMapper;
import com.yomeekoko.tredbase_payment_system.persistence.models.Rate;
import com.yomeekoko.tredbase_payment_system.persistence.repository.RateRepository;
import com.yomeekoko.tredbase_payment_system.service.interfaces.RateService;
import com.yomeekoko.tredbase_payment_system.utils.dto.RateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {

    private final RateRepository rateRepository;
    private final RateMapper rateMapper;

    @Override
    public RateResponse configureRate(RateRequest request) {
        Rate rate = rateMapper.toEntity(request);
        rate = rateRepository.save(rate);
        return rateMapper.toDTO(rate);
    }
    @Override
    public List<RateResponse> getAllRates() {
        return rateRepository.findAll()
                .stream()
                .map(rateMapper::toDTO)
                .collect(Collectors.toList());
    }
    public Double getCurrentRate() {
        Rate rate = rateRepository.findFirstByOrderByIdAsc(); // Assuming there's only one rate configuration
        if (rate != null) {
            return rate.getDynmaicRate();
        }
        throw new RuntimeException("No dynamic rate configuration found");
    }
}
