package com.mos.courier.common.enums;

import lombok.Getter;

public enum ErrorCodes {

    SUCCESS("M000"),

    SYSTEM_ERROR("M100");

    @Getter
    private final String code;

    ErrorCodes(String code) {
        this.code = code;
    }
}
