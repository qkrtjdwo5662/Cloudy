package com.cloudy.domain.member.exception;

import com.cloudy.global.error.BusinessException;
import com.cloudy.global.error.ErrorCode;

public class NormalRegisterFailException extends BusinessException {

    public NormalRegisterFailException(ErrorCode errorCode) {
        super(errorCode);
    }

    public NormalRegisterFailException(ErrorCode errorCode, String detailMessage) {
        super(errorCode, detailMessage);
    }
}
