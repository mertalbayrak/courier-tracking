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
import com.mos.courier.service.CourierTrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourierTrackingServiceImpl implements CourierTrackingService {

    private final RabbitMQProducer rabbitMQProducer;

    private final CourierLogDomainService courierLogDomainService;

    @Override
    public CourierMoveResponse courierMove(CourierMoveModel courierMoveModel) {
        CourierMoveResponse courierMoveResponse = new CourierMoveResponse();
        rabbitMQProducer.send(courierMoveModel);
        courierMoveResponse.setErrorCode(ErrorCodes.SUCCESS.getCode());
        courierMoveResponse.setErrorMessage("Success");
        return courierMoveResponse;
    }

    @Override
    public CourierTotalTravelDistanceResponse courierTotalTravelDistance(CourierTotalTravelDistanceModel courierTotalTravelDistanceModel) throws CourierTrackingException {
        CourierTotalTravelDistanceResponse courierTotalTravelDistanceResponse = new CourierTotalTravelDistanceResponse();
        List<CourierLog> courierLogList = courierLogDomainService.getCourierLogList(courierTotalTravelDistanceModel.getCourierId());
        if (courierLogList.isEmpty()) {
            throw new CourierTrackingException(ErrorCodes.SYSTEM_ERROR.getCode(), "Courier does not exist.");
        }
        courierTotalTravelDistanceResponse.setTotalTravelDistance(courierLogList.stream().mapToDouble(CourierLog::getTotalDistance).sum());
        courierTotalTravelDistanceResponse.setErrorCode(ErrorCodes.SUCCESS.getCode());
        courierTotalTravelDistanceResponse.setErrorMessage("Success");
        return courierTotalTravelDistanceResponse;
    }
}
