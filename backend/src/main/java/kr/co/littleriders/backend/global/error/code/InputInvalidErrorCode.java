package kr.co.littleriders.backend.global.error.code;

import org.springframework.http.HttpStatus;


public enum InputInvalidErrorCode implements LittleRidersErrorCode {


    ADDRESS(HttpStatus.BAD_REQUEST, "001", "주소가 올바르지 않습니다.");

    InputInvalidErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = "INPUT_" + code;
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
