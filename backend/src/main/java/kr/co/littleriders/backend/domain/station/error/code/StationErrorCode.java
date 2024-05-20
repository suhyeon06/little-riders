package kr.co.littleriders.backend.domain.station.error.code;

import kr.co.littleriders.backend.global.error.code.LittleRidersErrorCode;
import org.springframework.http.HttpStatus;


public enum StationErrorCode implements LittleRidersErrorCode {


    NOT_FOUND(HttpStatus.NOT_FOUND, "001", "정류장을 찾을 수 없습니다."),
    DUPLICATE_NAME(HttpStatus.CONFLICT, "002", "이미 존재하는 정류장 이름입니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "003", "해당 작업을 수행할 권한이 없습니다.");

    StationErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = "STATION_" + code;
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
