package com.cloudy.domain.member.exception;

import com.cloudy.global.error.BusinessException;
import com.cloudy.global.error.ErrorCode;

public class ReissueFailException extends BusinessException {

    public ReissueFailException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ReissueFailException(ErrorCode errorCode, String detailMessage) {
        super(errorCode, detailMessage);
    }
}
