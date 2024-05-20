package kr.co.littleriders.backend.domain.verification.error.exception;

import kr.co.littleriders.backend.domain.shuttle.error.code.ShuttleErrorCode;
import kr.co.littleriders.backend.domain.verification.error.code.VerificationErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.Getter;

@Getter
public final class VerificationException extends LittleRidersException {

    VerificationException(VerificationErrorCode errorCode) {
        super(errorCode);
    }

    public static VerificationException from(VerificationErrorCode errorCode) {
        return new VerificationException(errorCode);
    }

}
