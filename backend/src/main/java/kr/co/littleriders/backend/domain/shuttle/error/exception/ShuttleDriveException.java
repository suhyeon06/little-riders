package kr.co.littleriders.backend.domain.shuttle.error.exception;

import kr.co.littleriders.backend.domain.shuttle.error.code.ShuttleDriveErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.Getter;

@Getter
public final class ShuttleDriveException extends LittleRidersException {

    ShuttleDriveException(ShuttleDriveErrorCode errorCode) {
        super(errorCode);
    }

    public static ShuttleDriveException from(ShuttleDriveErrorCode errorCode) {
        return new ShuttleDriveException(errorCode);
    }

}
