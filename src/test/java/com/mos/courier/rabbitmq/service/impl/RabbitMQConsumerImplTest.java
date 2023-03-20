package com.mos.courier.rabbitmq.service.impl;

import com.mos.courier.domain.entity.CourierLog;
import com.mos.courier.domain.entity.Store;
import com.mos.courier.domain.service.CourierLogDomainService;
import com.mos.courier.domain.service.StoreDomainService;
import com.mos.courier.model.CourierMoveModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RabbitMQConsumerImplTest {

    @Mock
    private CourierLogDomainService courierLogDomainService;

    @Mock
    private StoreDomainService storeDomainService;

    @InjectMocks
    private RabbitMQConsumerImpl rabbitMQConsumer;

    @BeforeEach
    public void setUp() {
        rabbitMQConsumer = new RabbitMQConsumerImpl(courierLogDomainService, storeDomainService);
    }

    @Test
    public void test_recievedMessage() {
        CourierMoveModel courierMoveModel = new CourierMoveModel();
        courierMoveModel.setCourierId(1L);
        courierMoveModel.setLat(1D);
        courierMoveModel.setLng(1D);
        List<Store> storeList = new ArrayList<>();
        Store store = new Store();
        store.setId(1L);
        store.setLat(1D);
        store.setLng(1D);
        store.setName("a");
        storeList.add(store);
        Mockito.when(storeDomainService.fetchAllStores()).thenReturn(storeList);
        CourierLog courierLog = new CourierLog();
        courierLog.setLat(1D);
        courierLog.setLng(1D);
        Mockito.when(courierLogDomainService.fetchLastLogData(courierMoveModel.getCourierId())).thenReturn(courierLog);
        rabbitMQConsumer.recievedMessage(courierMoveModel);
    }
}