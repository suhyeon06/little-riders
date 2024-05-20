package kr.co.littleriders.backend.domain.teacher.error.exception;

import kr.co.littleriders.backend.domain.teacher.error.code.TeacherErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.Getter;

@Getter
public final class TeacherException extends LittleRidersException {

    TeacherException(TeacherErrorCode errorCode) {
        super(errorCode);
    }

    public static TeacherException from(TeacherErrorCode errorCode) {
        return new TeacherException(errorCode);
    }

}
