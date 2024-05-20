package kr.co.littleriders.backend.domain.token.error.exception;

import kr.co.littleriders.backend.domain.token.error.code.SignUpTokenErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.Getter;

@Getter
public final class SignUpTokenException extends LittleRidersException {

    SignUpTokenException(SignUpTokenErrorCode errorCode) {
        super(errorCode);
    }

    public static SignUpTokenException from(SignUpTokenErrorCode errorCode) {
        return new SignUpTokenException(errorCode);
    }

}
