package com.cloudy.global.config.security.jwt;

import com.cloudy.global.error.BusinessException;
import com.cloudy.global.error.ErrorCode;

public class CustomJwtException extends BusinessException {
    public CustomJwtException(ErrorCode errorCode) {
        super(errorCode);
    }

    public CustomJwtException(ErrorCode errorCode, String detailMessage) {
        super(errorCode, detailMessage);
    }
}
