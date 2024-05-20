package kr.co.littleriders.backend.domain.routeinfo.error.code;

import kr.co.littleriders.backend.global.error.code.LittleRidersErrorCode;
import org.springframework.http.HttpStatus;


public enum RouteStationErrorCode implements LittleRidersErrorCode {


    NOT_FOUND(HttpStatus.NOT_FOUND, "001", "해당 경로의 정류장을 찾을 수 없습니다.");


    RouteStationErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = "ROUTE_STATION_" + code;
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
