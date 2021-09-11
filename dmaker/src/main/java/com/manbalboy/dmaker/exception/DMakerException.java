package com.manbalboy.dmaker.exception;

import lombok.Getter;

@Getter
public class DMakerException extends RuntimeException {
    private DMakerErrorCode dMakerErrorCode;
    private String detailMessage;


    public DMakerException(DMakerErrorCode errorCode) {
        super(errorCode.getMessage());
        this.dMakerErrorCode = errorCode;
        this.detailMessage = errorCode.getMessage();
    }

    public DMakerException(DMakerErrorCode dMakerErrorCode, String detailMessage) {
        super(detailMessage);
        this.dMakerErrorCode = dMakerErrorCode;
        this.detailMessage = detailMessage;
    }
}
