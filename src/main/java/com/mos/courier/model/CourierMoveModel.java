package com.mos.courier.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourierMoveModel {

    private Long courierId;

    private Double lat;

    private Double lng;
}
