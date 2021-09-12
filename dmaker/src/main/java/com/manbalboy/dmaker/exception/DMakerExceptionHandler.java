package com.manbalboy.dmaker.exception;

import com.manbalboy.dmaker.dto.DMakerErrorResponse;
import com.manbalboy.dmaker.type.DMakerErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice()
public class DMakerExceptionHandler {

    @ExceptionHandler(DMakerException.class)
    public DMakerErrorResponse handlerException(DMakerException e,
                                                HttpServletRequest request) {
        log.error("errorCode: {} , url: {}, message: {}", e.getDMakerErrorCode(),
                request.getRequestURL(),
                e.getDetailMessage());

        return DMakerErrorResponse.builder()
                .errorCode(e.getDMakerErrorCode())
                .errorMessage(e.getDetailMessage())
                .build();
    }


    @ExceptionHandler(value = {
            HttpRequestMethodNotSupportedException.class,
            MethodArgumentNotValidException.class
    })
    public DMakerErrorResponse handleBadRequest(Exception e, HttpServletRequest request) {
        log.error(" url: {}, message: {}",
                request.getRequestURL(),
                e.getMessage());

        return DMakerErrorResponse.builder()
                .errorCode(DMakerErrorCode.INVALID_REQEUST)
                .errorMessage(DMakerErrorCode.INVALID_REQEUST.getMessage())
                .build();

    }


    @ExceptionHandler(Exception.class)
    public DMakerErrorResponse handleException(Exception e, HttpServletRequest request) {
        log.error(" url: {}, message: {}",
                request.getRequestURL(),
                e.getMessage());

        return DMakerErrorResponse.builder()
                .errorCode(DMakerErrorCode.INVALID_REQEUST)
                .errorMessage(DMakerErrorCode.INVALID_REQEUST.getMessage())
                .build();

    }
}
