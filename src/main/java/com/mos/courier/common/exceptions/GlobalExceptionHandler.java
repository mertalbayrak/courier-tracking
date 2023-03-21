package com.mos.courier.common.exceptions;

import com.mos.courier.api.response.BaseResponse;
import com.mos.courier.common.enums.ErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = CourierTrackingException.class)
    @ResponseStatus(code = HttpStatus.OK)
    public BaseResponse handleBusinessException(CourierTrackingException courierTrackingException) {
        log.error("handleBusinessExceptions : " + courierTrackingException.getMessage());
        return createResponse(courierTrackingException.getErrorCode(), courierTrackingException.getErrorMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(code = HttpStatus.OK)
    public BaseResponse handleException(Exception ex) {
        log.error("handleException : " + ex.getMessage());
        return createResponse(ErrorCodes.SYSTEM_ERROR.getCode(), "An error has occurred.");
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public BaseResponse handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        log.error("handleHttpRequestMethodNotSupported : " + ex.getMessage());
        return createResponse(ErrorCodes.SYSTEM_ERROR.getCode(), "Check your http request method.");
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public BaseResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        log.error("handleMethodArgumentNotValid : " + ex.getMessage());
        return createResponse(ErrorCodes.SYSTEM_ERROR.getCode(), "Input values is not valid.");
    }

    @ExceptionHandler(value = HttpMediaTypeNotAcceptableException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public BaseResponse handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex) {
        log.error("handleHttpMediaTypeNotAcceptable : " + ex.getMessage());
        return createResponse(ErrorCodes.SYSTEM_ERROR.getCode(), "Check your http media type.");
    }

    @ExceptionHandler(value = MissingPathVariableException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public BaseResponse handleMissingPathVariable(MissingPathVariableException ex) {
        log.error("handleMissingPathVariable : " + ex.getMessage());
        return createResponse(ErrorCodes.SYSTEM_ERROR.getCode(), "There is a missing path variable.");
    }

    private BaseResponse createResponse(String errorCode, String errorMessage) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setErrorCode(errorCode);
        baseResponse.setErrorMessage(errorMessage);
        return baseResponse;
    }
}
