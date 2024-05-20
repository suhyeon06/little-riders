package kr.co.littleriders.backend.domain.shuttle.error.exception;

import kr.co.littleriders.backend.domain.shuttle.error.code.ShuttleBoardErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.Getter;

@Getter
public final class ShuttleBoardException extends LittleRidersException {

    ShuttleBoardException(ShuttleBoardErrorCode errorCode) {
        super(errorCode);
    }

    public static ShuttleBoardException from(ShuttleBoardErrorCode errorCode) {
        return new ShuttleBoardException(errorCode);
    }

}
