package kr.co.littleriders.backend.domain.academy.error.exception;


import kr.co.littleriders.backend.domain.academy.error.code.AcademyErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.Getter;

@Getter
public final class AcademyException extends LittleRidersException {
    AcademyException(AcademyErrorCode errorCode) {
        super(errorCode);
    }

    public static AcademyException from(AcademyErrorCode errorCode) {
        return new AcademyException(errorCode);
    }

}
