package kr.co.littleriders.backend.domain.teacher.error.code;

import kr.co.littleriders.backend.global.error.code.LittleRidersErrorCode;
import org.springframework.http.HttpStatus;


public enum TeacherErrorCode implements LittleRidersErrorCode {


    NOT_FOUND(HttpStatus.NOT_FOUND, "001", "해당 교사를 찾을수 없습니다."),
    ILLEGAL_ACCESS(HttpStatus.BAD_REQUEST, "002", "접근 권한이 없습니다");


    TeacherErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = "TEACHER_" + code;
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
