package kr.co.littleriders.backend.domain.beacon.error.code;

import org.springframework.http.HttpStatus;

import kr.co.littleriders.backend.global.error.code.LittleRidersErrorCode;

public enum BeaconErrorCode implements LittleRidersErrorCode {


    NOT_FOUND(HttpStatus.NOT_FOUND, "001", "비콘를 찾을수 없습니다.");


    BeaconErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = "BEACON_" + code;
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
