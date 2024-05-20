package kr.co.littleriders.backend.domain.academy.error.exception;


import kr.co.littleriders.backend.domain.academy.error.code.AcademyChildErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.Getter;

@Getter
public final class AcademyChildException extends LittleRidersException {

    AcademyChildException(AcademyChildErrorCode errorCode) {
        super(errorCode);
    }

    public static AcademyChildException from(AcademyChildErrorCode errorCode) {
        return new AcademyChildException(errorCode);
    }

}
