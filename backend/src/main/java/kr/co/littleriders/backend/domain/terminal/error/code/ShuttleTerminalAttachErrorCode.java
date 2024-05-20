package kr.co.littleriders.backend.domain.terminal.error.code;

import kr.co.littleriders.backend.global.error.code.LittleRidersErrorCode;
import org.springframework.http.HttpStatus;


public enum ShuttleTerminalAttachErrorCode implements LittleRidersErrorCode {


    NOT_FOUND(HttpStatus.NOT_FOUND, "001", "부착된 셔틀을 찾을수 없습니다."); //TODO : 진짜 뭐넣지


    ShuttleTerminalAttachErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = "SHUTTLE_TERMINAL_ATTACH_" + code;
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
