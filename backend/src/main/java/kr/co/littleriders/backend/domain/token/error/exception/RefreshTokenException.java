package kr.co.littleriders.backend.domain.token.error.exception;

import kr.co.littleriders.backend.domain.token.error.code.RefreshTokenErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.Getter;

@Getter
public final class RefreshTokenException extends LittleRidersException {

    RefreshTokenException(RefreshTokenErrorCode errorCode) {
        super(errorCode);
    }

    public static RefreshTokenException from(RefreshTokenErrorCode errorCode) {
        return new RefreshTokenException(errorCode);
    }

}
