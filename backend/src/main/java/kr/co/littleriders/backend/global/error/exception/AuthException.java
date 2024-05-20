package kr.co.littleriders.backend.global.error.exception;

import kr.co.littleriders.backend.global.error.code.AuthErrorCode;
import lombok.Getter;

@Getter
public final class AuthException extends LittleRidersException {

    AuthException(AuthErrorCode errorCode) {
        super(errorCode);
    }

    public static AuthException from(AuthErrorCode errorCode) {
        return new AuthException(errorCode);
    }

}
