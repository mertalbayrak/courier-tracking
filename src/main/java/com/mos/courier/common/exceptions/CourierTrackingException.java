package com.mos.courier.common.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourierTrackingException extends Exception {

    private final String errorCode;

    private final String errorMessage;

    public CourierTrackingException(String errorCode, String errorMessage) {
        super(errorCode + "-" + errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
