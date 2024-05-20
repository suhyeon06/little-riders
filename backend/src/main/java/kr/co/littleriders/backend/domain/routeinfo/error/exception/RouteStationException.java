package kr.co.littleriders.backend.domain.routeinfo.error.exception;

import kr.co.littleriders.backend.domain.routeinfo.error.code.RouteStationErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.Getter;

@Getter
public final class RouteStationException extends LittleRidersException {
    
    RouteStationException(RouteStationErrorCode errorCode) {
        super(errorCode);

    }

    public static RouteStationException from(RouteStationErrorCode errorCode) {
        return new RouteStationException(errorCode);
    }

}
