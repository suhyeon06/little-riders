package kr.co.littleriders.backend.global.error.exception;

import kr.co.littleriders.backend.global.error.code.InputInvalidErrorCode;
import lombok.Getter;

@Getter
public final class InputInvalidException extends LittleRidersException {

    InputInvalidException(InputInvalidErrorCode errorCode) {
        super(errorCode);
    }

    public static InputInvalidException from(InputInvalidErrorCode errorCode) {
        return new InputInvalidException(errorCode);
    }

}
