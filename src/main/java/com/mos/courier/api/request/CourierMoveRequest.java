package com.mos.courier.api.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourierMoveRequest extends BaseRequest {

    @NotNull(message = "courierId must not be null")
    private Long courierId;

    @Digits(integer = 2, fraction = 7, message = "lat must be valid")
    private Double lat;

    @Digits(integer = 2, fraction = 7, message = "lng must be valid")
    private Double lng;
}