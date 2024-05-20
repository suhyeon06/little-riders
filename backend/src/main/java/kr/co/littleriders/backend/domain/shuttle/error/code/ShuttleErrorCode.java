package kr.co.littleriders.backend.domain.shuttle.error.code;

import kr.co.littleriders.backend.global.error.code.LittleRidersErrorCode;
import org.springframework.http.HttpStatus;


public enum ShuttleErrorCode implements LittleRidersErrorCode {


    NOT_FOUND(HttpStatus.NOT_FOUND, "001", "셔틀을 찾을 수 없습니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "002", "해당 경로에 접근할 권한이 없습니다.");



    ShuttleErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = "SHUTTLE_" + code;
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
