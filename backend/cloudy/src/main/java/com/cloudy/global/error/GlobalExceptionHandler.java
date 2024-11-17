package com.cloudy.global.error;

import com.cloudy.global.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<Response<ErrorInfo>> businessExceptionHandler(BusinessException e){
        log.error("Businessexception - code: {} / message: {} / class: {}", e.getErrorCode().getCode(), e.getErrorCode().getMessage(), e.getClass());
        return ResponseEntity.status(e.getErrorCode().getStatus()).body(Response.ERROR(e.getErrorCode(), e.getMessage()));
    }

    @ExceptionHandler(NumberFormatException.class)
    protected ResponseEntity<Response<ErrorInfo>> handleNumberFormatException(Exception ex){
        log.error("NumberFormatException - message: {} / class: {}", ex.getMessage(), ex.getClass());
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Response.ERROR(ErrorCode.INTERNAL_SERVER_ERROR, ex.getMessage()));
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    protected ResponseEntity<Response<ErrorInfo>> handleAuthorizationDeniedException(Exception ex){
        log.error("AuthorizationDeniedException - message: {} / class: {}", ex.getMessage(), ex.getClass());
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Response.ERROR(ErrorCode.UNAUTHORIZED, ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Response<ErrorInfo>> handleException(Exception ex){
        log.error("Exception - message: {} / class: {}", ex.getMessage(), ex.getClass());
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Response.ERROR(ErrorCode.INTERNAL_SERVER_ERROR, ex.getMessage()));
    }

}