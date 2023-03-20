package com.mos.courier.controller;

import com.mos.courier.api.CourierTrackingApi;
import com.mos.courier.api.request.CourierMoveRequest;
import com.mos.courier.api.request.CourierTotalTravelDistanceRequest;
import com.mos.courier.api.response.CourierMoveResponse;
import com.mos.courier.api.response.CourierTotalTravelDistanceResponse;
import com.mos.courier.common.exceptions.CourierTrackingException;
import com.mos.courier.common.mapper.ModelMapperService;
import com.mos.courier.model.CourierMoveModel;
import com.mos.courier.model.CourierTotalTravelDistanceModel;
import com.mos.courier.service.CourierTrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CourierTrackingController implements CourierTrackingApi {

    private final CourierTrackingService courierTrackingService;

    private final ModelMapperService modelMapperService;

    @Override
    public CourierMoveResponse courierMove(CourierMoveRequest courierMoveRequest) {
        return courierTrackingService.courierMove(modelMapperService.forRequestMap(courierMoveRequest, CourierMoveModel.class));
    }

    @Override
    public CourierTotalTravelDistanceResponse courierTotalTravelDistance(CourierTotalTravelDistanceRequest courierTotalTravelDistanceRequest) throws CourierTrackingException {
        return courierTrackingService.courierTotalTravelDistance(modelMapperService.forRequestMap(courierTotalTravelDistanceRequest, CourierTotalTravelDistanceModel.class));
    }
}
