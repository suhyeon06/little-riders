package kr.co.littleriders.backend.domain.academy.error.code;

import kr.co.littleriders.backend.global.error.code.LittleRidersErrorCode;
import org.springframework.http.HttpStatus;


public enum AcademyChildErrorCode implements LittleRidersErrorCode {


    NOT_FOUND(HttpStatus.NOT_FOUND, "001", "학원 어린이를 찾을수 없습니다."),
    ILLEGAL_ACCESS(HttpStatus.BAD_REQUEST, "002", "접근 권한이 없습니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "003", "재학 중이 아닌 원생은 업데이트가 불가합니다.");


    AcademyChildErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = "ACADEMY_CHILD_" + code;
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
