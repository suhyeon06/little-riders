package kr.co.littleriders.backend.domain.shuttle.error.code;

import kr.co.littleriders.backend.global.error.code.LittleRidersErrorCode;
import org.springframework.http.HttpStatus;


public enum ShuttleBoardErrorCode implements LittleRidersErrorCode {


    NOT_FOUND(HttpStatus.NOT_FOUND, "001", "승차 정보를 찾을수 없습니다."),
    ALREADY_BOARD(HttpStatus.CONFLICT,"002","이미 승차처리된 학생입니다.");

    ShuttleBoardErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = "SHUTTLE_BOARD_" + code;
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
