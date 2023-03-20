package com.mos.courier.api;

import com.mos.courier.api.request.CourierMoveRequest;
import com.mos.courier.api.request.CourierTotalTravelDistanceRequest;
import com.mos.courier.api.response.CourierMoveResponse;
import com.mos.courier.api.response.CourierTotalTravelDistanceResponse;
import com.mos.courier.common.exceptions.CourierTrackingException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface CourierTrackingApi {

    @PostMapping("/courierMove")
    CourierMoveResponse courierMove(@RequestBody @Valid CourierMoveRequest courierMoveRequest);

    @GetMapping("/courierTotalTravelDistance")
    CourierTotalTravelDistanceResponse courierTotalTravelDistance(@RequestBody @Valid CourierTotalTravelDistanceRequest courierTotalTravelDistanceRequest) throws CourierTrackingException;
}
