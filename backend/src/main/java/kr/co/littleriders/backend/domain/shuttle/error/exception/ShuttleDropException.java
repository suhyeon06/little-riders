package kr.co.littleriders.backend.domain.shuttle.error.exception;

import kr.co.littleriders.backend.domain.shuttle.error.code.ShuttleDropErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.Getter;

@Getter
public final class ShuttleDropException extends LittleRidersException {

    ShuttleDropException(ShuttleDropErrorCode errorCode) {
        super(errorCode);
    }

    public static ShuttleDropException from(ShuttleDropErrorCode errorCode) {
        return new ShuttleDropException(errorCode);
    }

}
