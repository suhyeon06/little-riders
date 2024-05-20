package kr.co.littleriders.backend.domain.route.error.code;

import kr.co.littleriders.backend.global.error.code.LittleRidersErrorCode;
import org.springframework.http.HttpStatus;


public enum RouteErrorCode implements LittleRidersErrorCode {


    NOT_FOUND(HttpStatus.NOT_FOUND, "001", "경로를 찾을수 없습니다."),
    DUPLICATE_NAME(HttpStatus.CONFLICT, "002", "이미 존재하는 경로 이름입니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "003", "해당 데이터에 접근할 권한이 없습니다.");

    RouteErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = "ROUTE_" + code;
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
