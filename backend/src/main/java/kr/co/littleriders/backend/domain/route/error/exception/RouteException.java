package kr.co.littleriders.backend.domain.route.error.exception;

import kr.co.littleriders.backend.domain.route.error.code.RouteErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.Getter;

@Getter
public final class RouteException extends LittleRidersException {


    RouteException(RouteErrorCode errorCode) {
        super(errorCode);

    }

    public static RouteException from(RouteErrorCode errorCode) {
        return new RouteException(errorCode);
    }

}
