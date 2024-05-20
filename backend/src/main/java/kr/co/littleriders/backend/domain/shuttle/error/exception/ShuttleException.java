package kr.co.littleriders.backend.domain.shuttle.error.exception;

import kr.co.littleriders.backend.domain.shuttle.error.code.ShuttleErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.Getter;

@Getter
public final class ShuttleException extends LittleRidersException {


    ShuttleException(ShuttleErrorCode errorCode) {
        super(errorCode);
    }

    public static ShuttleException from(ShuttleErrorCode errorCode) {
        return new ShuttleException(errorCode);
    }

}
