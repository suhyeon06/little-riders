package kr.co.littleriders.backend.global.error.exception;

import kr.co.littleriders.backend.global.error.code.AuthErrorCode;
import kr.co.littleriders.backend.global.error.code.MemberErrorCode;
import lombok.Getter;

@Getter
public final class MemberException extends LittleRidersException {

    MemberException(MemberErrorCode errorCode) {
        super(errorCode);
    }

    public static MemberException from(MemberErrorCode errorCode) {
        return new MemberException(errorCode);
    }

}
