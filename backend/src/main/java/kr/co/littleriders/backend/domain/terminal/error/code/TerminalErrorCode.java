package kr.co.littleriders.backend.domain.terminal.error.code;

import kr.co.littleriders.backend.global.error.code.LittleRidersErrorCode;
import org.springframework.http.HttpStatus;


public enum TerminalErrorCode implements LittleRidersErrorCode {


    NOT_FOUND(HttpStatus.NOT_FOUND, "001", "단말기를 찾을수 없습니다."),
    ALREADY_REGISTERED(HttpStatus.CONFLICT,"002", "이미 등록된 단말기 입니다.");


    TerminalErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = "TERMINAL_" + code;
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
