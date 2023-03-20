package com.mos.courier.service.impl;

import com.mos.courier.api.response.CourierMoveResponse;
import com.mos.courier.api.response.CourierTotalTravelDistanceResponse;
import com.mos.courier.common.enums.ErrorCodes;
import com.mos.courier.common.exceptions.CourierTrackingException;
import com.mos.courier.domain.entity.CourierLog;
import com.mos.courier.domain.service.CourierLogDomainService;
import com.mos.courier.model.CourierMoveModel;
import com.mos.courier.model.CourierTotalTravelDistanceModel;
import com.mos.courier.rabbitmq.service.RabbitMQProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CourierTrackingServiceImplTest {

    @Mock
    private RabbitMQProducer rabbitMQProducer;

    @Mock
    private CourierLogDomainService courierLogDomainService;

    @InjectMocks
    private CourierTrackingServiceImpl courierTrackingService;

    @BeforeEach
    public void setUp() {
        courierTrackingService = new CourierTrackingServiceImpl(rabbitMQProducer, courierLogDomainService);
    }

    @Test
    public void test_courierMove_success() {
        CourierMoveModel courierMoveModel = new CourierMoveModel();
        courierMoveModel.setCourierId(1L);
        courierMoveModel.setLat(1D);
        courierMoveModel.setLng(1D);
        CourierMoveResponse courierMoveResponse = courierTrackingService.courierMove(courierMoveModel);
        assertEquals(ErrorCodes.SUCCESS.getCode(), courierMoveResponse.getErrorCode());
    }

    @Test
    public void test_courierTotalTravelDistance_success() throws CourierTrackingException {
        CourierTotalTravelDistanceModel courierTotalTravelDistanceModel = new CourierTotalTravelDistanceModel();
        courierTotalTravelDistanceModel.setCourierId(1L);
        List<CourierLog> courierLogList = new ArrayList<>();
        CourierLog courierLog = new CourierLog();
        courierLog.setTotalDistance(1D);
        courierLogList.add(courierLog);
        Mockito.when(courierLogDomainService.getCourierLogList(courierTotalTravelDistanceModel.getCourierId())).thenReturn(courierLogList);
        CourierTotalTravelDistanceResponse courierTotalTravelDistanceResponse = courierTrackingService.courierTotalTravelDistance(courierTotalTravelDistanceModel);
        assertEquals(ErrorCodes.SUCCESS.getCode(), courierTotalTravelDistanceResponse.getErrorCode());
    }

    @Test
    public void test_courierTotalTravelDistance_error() {
        CourierTotalTravelDistanceModel courierTotalTravelDistanceModel = new CourierTotalTravelDistanceModel();
        courierTotalTravelDistanceModel.setCourierId(1L);
        try {
            courierTrackingService.courierTotalTravelDistance(courierTotalTravelDistanceModel);
        } catch (CourierTrackingException e) {
            assertEquals(ErrorCodes.SYSTEM_ERROR.getCode(), e.getErrorCode());
        }

    }
}