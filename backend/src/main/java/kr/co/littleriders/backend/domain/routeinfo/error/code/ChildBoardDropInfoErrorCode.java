package kr.co.littleriders.backend.domain.routeinfo.error.code;

import kr.co.littleriders.backend.global.error.code.LittleRidersErrorCode;
import org.springframework.http.HttpStatus;


public enum ChildBoardDropInfoErrorCode implements LittleRidersErrorCode {


    NOT_FOUND(HttpStatus.NOT_FOUND, "001", "원생의 승차/하차 정류장 정보를 찾을 수 없습니다.");


    ChildBoardDropInfoErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = "CHILD_BOARD_DROP_INFO_" + code;
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
