package kr.co.littleriders.backend.domain.shuttle.error.code;

import kr.co.littleriders.backend.global.error.code.LittleRidersErrorCode;
import org.springframework.http.HttpStatus;


public enum DriveUniqueKeyErrorCode implements LittleRidersErrorCode {


    NOT_FOUND(HttpStatus.NOT_FOUND, "001", "해당 정보를 찾을수 없습니다.");

    DriveUniqueKeyErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = "DRIVE_UNIQUE_KEY_" + code;
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
