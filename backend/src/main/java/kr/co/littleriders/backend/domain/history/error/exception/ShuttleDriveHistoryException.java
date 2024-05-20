package kr.co.littleriders.backend.domain.history.error.exception;

import kr.co.littleriders.backend.domain.history.error.code.ShuttleDriveHistoryErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.Getter;

@Getter
public final class ShuttleDriveHistoryException extends LittleRidersException {

    ShuttleDriveHistoryException(ShuttleDriveHistoryErrorCode errorCode) {
        super(errorCode);
    }

    public static ShuttleDriveHistoryException from(ShuttleDriveHistoryErrorCode errorCode) {
        return new ShuttleDriveHistoryException(errorCode);
    }

}
