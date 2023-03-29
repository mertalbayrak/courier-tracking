package com.mos.courier.api.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourierMoveRequest extends BaseRequest {

    @NotNull(message = "lat must not be null")
    @Digits(integer = 2, fraction = 7, message = "lat must be valid")
    private Double lat;

    @NotNull(message = "lng must not be null")
    @Digits(integer = 2, fraction = 7, message = "lng must be valid")
    private Double lng;
}
