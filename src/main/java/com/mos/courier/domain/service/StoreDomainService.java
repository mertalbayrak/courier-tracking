package com.mos.courier.domain.service;

import com.mos.courier.domain.entity.Store;
import com.mos.courier.domain.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreDomainService {

    private final StoreRepository storeRepository;

    public List<Store> fetchAllStores() {
        return storeRepository.findAll();
    }
}
