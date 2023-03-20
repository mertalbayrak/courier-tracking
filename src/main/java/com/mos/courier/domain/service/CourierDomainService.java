package com.mos.courier.domain.service;

import com.mos.courier.domain.entity.Courier;
import com.mos.courier.domain.repository.CourierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourierDomainService {

    private final CourierRepository courierRepository;

    public void saveCourier(Courier courier) {
        courier.setCourierName(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
        courierRepository.save(courier);
    }
}
