package com.cloudy.domain.member.exception;

import com.cloudy.global.error.BusinessException;
import com.cloudy.global.error.ErrorCode;

public class MemberNotFoundException extends BusinessException {

    public MemberNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public MemberNotFoundException(ErrorCode errorCode, String detailMessage) {
        super(errorCode, detailMessage);
    }
}