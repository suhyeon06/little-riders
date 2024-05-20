package kr.co.littleriders.backend.domain.history.error.code;

import kr.co.littleriders.backend.global.error.code.LittleRidersErrorCode;
import org.springframework.http.HttpStatus;


public enum BoardDropHistoryErrorCode implements LittleRidersErrorCode {


    NOT_FOUND(HttpStatus.NOT_FOUND, "001", "승차 기록을 찾을수 없습니다"),
    ILLEGAL_ACCESS(HttpStatus.BAD_REQUEST, "002", "접근 권한이 없습니다");


    BoardDropHistoryErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = "BOARD_DROP_HISTORY_" + code;
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
