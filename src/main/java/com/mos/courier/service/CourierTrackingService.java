package com.mos.courier.service;

import com.mos.courier.api.response.CourierMoveResponse;
import com.mos.courier.api.response.CourierTotalTravelDistanceResponse;
import com.mos.courier.common.exceptions.CourierTrackingException;
import com.mos.courier.model.CourierMoveModel;
import com.mos.courier.model.CourierTotalTravelDistanceModel;


public interface CourierTrackingService {

    CourierMoveResponse courierMove(CourierMoveModel courierMoveModel);

    CourierTotalTravelDistanceResponse courierTotalTravelDistance(CourierTotalTravelDistanceModel courierTotalTravelDistanceModel) throws CourierTrackingException;
}
