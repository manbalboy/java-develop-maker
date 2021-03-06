package com.manbalboy.dmaker.exception;

import com.manbalboy.dmaker.type.DMakerErrorCode;
import lombok.Getter;

@Getter
public class DMakerException extends RuntimeException {
    private final DMakerErrorCode dMakerErrorCode;
    private final String detailMessage;


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
