package kr.co.littleriders.backend.domain.token.error.code;

import kr.co.littleriders.backend.global.error.code.LittleRidersErrorCode;
import org.springframework.http.HttpStatus;


public enum RefreshTokenErrorCode implements LittleRidersErrorCode {


    NOT_FOUND(HttpStatus.NOT_FOUND, "001", "해당 토큰을 찾을수 없습니다."); //TODO : 진짜 뭐넣지


    RefreshTokenErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = "REFRESH_TOKEN_" + code;
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
