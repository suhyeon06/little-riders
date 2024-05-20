package kr.co.littleriders.backend.domain.shuttle.error.code;

import kr.co.littleriders.backend.global.error.code.LittleRidersErrorCode;
import org.springframework.http.HttpStatus;


public enum ShuttleChildRideErrorCode implements LittleRidersErrorCode {


    NOT_FOUND(HttpStatus.NOT_FOUND, "001", "원생 승하차 정보를 찾을 수 없습니다."),
    INVALID_RIDE_STATUS(HttpStatus.UNPROCESSABLE_ENTITY, "002", "승차/하차 여부를 판별할 수 없습니다.");

    ShuttleChildRideErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = "SHUTTLE_CHILD_RIDE_" + code;
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
