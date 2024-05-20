package kr.co.littleriders.backend.global.error.exception;


import kr.co.littleriders.backend.global.error.code.LittleRidersErrorCode;
import lombok.Getter;

@Getter
public class LittleRidersException extends RuntimeException {

    private final LittleRidersErrorCode errorCode;

    protected LittleRidersException(LittleRidersErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    protected static LittleRidersException from(LittleRidersErrorCode errorCode) {
        return new LittleRidersException(errorCode);
    }

}
