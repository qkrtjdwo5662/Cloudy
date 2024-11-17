package com.cloudy.global.config.swagger;

import com.cloudy.global.error.ErrorCode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SwaggerApiError {

    ErrorCode[] value();
}