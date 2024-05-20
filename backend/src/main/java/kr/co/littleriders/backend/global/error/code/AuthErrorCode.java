package kr.co.littleriders.backend.global.error.code;

import org.springframework.http.HttpStatus;


public enum AuthErrorCode implements LittleRidersErrorCode {


    JWT_EXPIRED(HttpStatus.UNAUTHORIZED, "001", "토큰이 만료되었습니다."),
    JWT_NOT_SUPPORT(HttpStatus.BAD_REQUEST, "002", "지원하지 않는 토큰입니다."),
    JWT_KEY_NOT_VALID(HttpStatus.INTERNAL_SERVER_ERROR, "003", "알 수 없는 오류가 발생했습니다."),

    JWT_NOT_FOUND(HttpStatus.BAD_REQUEST,"004","토큰을 찾을수 없습니다."),
    AUTHORIZATION_NOT_VALID(HttpStatus.BAD_REQUEST,"005","토큰은 Bearer 로 시작해야 합니다."),

    PASSWORD_NOT_EQUAL(HttpStatus.BAD_REQUEST,"006","비밀번호가 일치하지 않습니다"),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND,"007","일치하는 정보를 찾을수 없습니다"),
    UNKNOWN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"008","알 수 없는 오류가 발생했습니다"),
    NOT_VALID_REQUEST(HttpStatus.BAD_REQUEST,"009","잘못된 요청입니다"),
    SIGNATURE_NOT_VALID(HttpStatus.BAD_REQUEST,"010","잘못된 서명입니다");


    AuthErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = "AUTH_" + code;
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
