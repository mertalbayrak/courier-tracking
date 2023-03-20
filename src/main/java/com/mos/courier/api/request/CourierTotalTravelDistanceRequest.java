package com.mos.courier.api.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourierTotalTravelDistanceRequest extends BaseRequest {

    @NotNull(message = "courierId must not be null")
    private Long courierId;
}
