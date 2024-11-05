package com.cloudy.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    /**
     * Error Code 400 : 잘못된 요청 401 : 권한 오류 403 : 서버가 허용하지 않은 웹페이지, 미디어 요청 404 : 존재하지 않는 정보에 대한 요청
     */

    //common
    NOT_FOUND_DATA(404, "C001", "해당하는 데이터를 찾을 수 없습니다."),
    BAD_REQUEST(400, "C002", "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR(500, "C003", "서버 에러입니다"),
    UNAUTHORIZED(403, "C004", "권한이 없습니다."),
    JSON_PARSING_ERROR(500, "C005", "JSON 파싱을 실패했습니다."),
    FIREBASE_ERROR(500, "C006", "Firebase function 요청에 실패했습니다."),

    //jwt
    INVALID_JWT_TOKEN(400, "J001", "유효하지 않은 JWT 토큰입니다."),
    EXPIRED_JWT_TOKEN(400, "J002", "만료된 access 토큰입니다."),
    UNSUPPORTED_JWT_TOKEN(400, "J003", "지원되지 않는 JWT 토큰입니다."),
    ILLEGAL_JWT_TOKEN(400, "J004", "잘못된 JWT 토큰입니다."),
    NULL_JWT_TOKEN(400, "J005", "JWT 토큰 값이 넘어오지 않았습니다."),
    INVALID_BEARER_JWT_TOKEN(400, "J006", "토큰이 Bearer로 시작하지 않습니다."),
    EXPIRED_REFRESH_TOKEN(400, "J007", "만료된 refresh 토큰입니다."),

    //member
    INVALID_TOKEN(400, "M001", "유효하지 않은 토큰입니다."),
    NOT_EXIST_MEMBER(400, "M002", "존재하지 않는 회원입니다."),
    DUPLICATED_MEMBER(400, "M003", "이미 존재하는 회원입니다."),
    NOT_MATCH_PASSWORD(400, "M004", "비밀번호가 일치하지 않습니다."),
    ALREADY_WITHDRAWN_MEMBER(400, "M005","이미 탈퇴 처리된 회원입니다."),

    //company
    NOT_EXIST_COMPANY(400, "C001", "존재하지 않는 회사입니다.");





    private final Integer status;
    private final String code;
    private final String message;
}