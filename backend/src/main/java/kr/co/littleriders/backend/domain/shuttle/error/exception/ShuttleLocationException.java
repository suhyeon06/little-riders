package kr.co.littleriders.backend.domain.shuttle.error.exception;

import kr.co.littleriders.backend.domain.shuttle.error.code.ShuttleLocationErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.Getter;

@Getter
public final class ShuttleLocationException extends LittleRidersException {

    ShuttleLocationException(ShuttleLocationErrorCode errorCode) {
        super(errorCode);
    }

    public static ShuttleLocationException from(ShuttleLocationErrorCode errorCode) {
        return new ShuttleLocationException(errorCode);
    }

}
