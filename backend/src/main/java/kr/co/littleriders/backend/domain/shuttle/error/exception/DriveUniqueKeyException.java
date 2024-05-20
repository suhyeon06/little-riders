package kr.co.littleriders.backend.domain.shuttle.error.exception;

import kr.co.littleriders.backend.domain.shuttle.error.code.DriveUniqueKeyErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.Getter;

@Getter
public final class DriveUniqueKeyException extends LittleRidersException {

    DriveUniqueKeyException(DriveUniqueKeyErrorCode errorCode) {
        super(errorCode);
    }

    public static DriveUniqueKeyException from(DriveUniqueKeyErrorCode errorCode) {
        return new DriveUniqueKeyException(errorCode);
    }

}
