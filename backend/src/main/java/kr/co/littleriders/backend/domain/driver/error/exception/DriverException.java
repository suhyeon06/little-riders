package kr.co.littleriders.backend.domain.driver.error.exception;

import kr.co.littleriders.backend.domain.driver.error.code.DriverErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.Getter;

@Getter
public final class DriverException extends LittleRidersException {



    DriverException(DriverErrorCode errorCode) {
        super(errorCode);
    }

    public static DriverException from(DriverErrorCode errorCode) {
        return new DriverException(errorCode);
    }

}
