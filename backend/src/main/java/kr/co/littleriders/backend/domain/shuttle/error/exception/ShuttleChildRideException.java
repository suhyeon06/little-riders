package kr.co.littleriders.backend.domain.shuttle.error.exception;

import kr.co.littleriders.backend.domain.shuttle.error.code.ShuttleChildRideErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.Getter;

@Getter
public final class ShuttleChildRideException extends LittleRidersException {

    ShuttleChildRideException(ShuttleChildRideErrorCode errorCode) {
        super(errorCode);
    }

    public static ShuttleChildRideException from(ShuttleChildRideErrorCode errorCode) {
        return new ShuttleChildRideException(errorCode);
    }

}
