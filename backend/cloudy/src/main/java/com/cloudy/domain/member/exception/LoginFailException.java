package com.cloudy.domain.member.exception;

import com.cloudy.global.error.BusinessException;
import com.cloudy.global.error.ErrorCode;

public class LoginFailException extends BusinessException {

    public LoginFailException(ErrorCode errorCode) {
        super(errorCode);
    }

    public LoginFailException(ErrorCode errorCode, String detailMessage) {
        super(errorCode, detailMessage);
    }
}
