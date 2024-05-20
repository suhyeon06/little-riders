package kr.co.littleriders.backend.domain.routeinfo.error.exception;

import kr.co.littleriders.backend.domain.routeinfo.error.code.ChildBoardDropInfoErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.Getter;

@Getter
public final class ChildBoardDropInfoException extends LittleRidersException {

    ChildBoardDropInfoException(ChildBoardDropInfoErrorCode errorCode) {
        super(errorCode);

    }

    public static ChildBoardDropInfoException from(ChildBoardDropInfoErrorCode errorCode) {
        return new ChildBoardDropInfoException(errorCode);
    }

}
