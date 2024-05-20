package kr.co.littleriders.backend.global.error.code;

import org.springframework.http.HttpStatus;


public enum MemberErrorCode implements LittleRidersErrorCode {


    ALREADY_EMAIL_EXIST(HttpStatus.CONFLICT, "001","이미 사용중인 이메일입니다");

    MemberErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = "MEMBER_" + code;
        this.message = message;
    }

    private final HttpStatus status;
    private final String code;
    private final String message;


    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
