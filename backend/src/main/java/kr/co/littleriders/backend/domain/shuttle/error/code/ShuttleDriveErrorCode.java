package kr.co.littleriders.backend.domain.shuttle.error.code;

import kr.co.littleriders.backend.global.error.code.LittleRidersErrorCode;
import org.springframework.http.HttpStatus;


public enum ShuttleDriveErrorCode implements LittleRidersErrorCode {


    NOT_FOUND(HttpStatus.NOT_FOUND, "001", "셔틀 운행 시작 정보를 찾을 수 없습니다."),
    ALREADY_DRIVE(HttpStatus.CONFLICT,"002","이미 셔틀버스가 운행중입니다.");

    ShuttleDriveErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = "SHUTTLE_DRIVE_" + code;
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
