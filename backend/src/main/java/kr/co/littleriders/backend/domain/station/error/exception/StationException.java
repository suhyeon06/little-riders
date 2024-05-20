package kr.co.littleriders.backend.domain.station.error.exception;

import kr.co.littleriders.backend.domain.station.error.code.StationErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.Getter;

@Getter
public final class StationException extends LittleRidersException {

    StationException(StationErrorCode errorCode) {
        super(errorCode);
    }

    public static StationException from(StationErrorCode errorCode) {
        return new StationException(errorCode);
    }

}
