package kr.co.littleriders.backend.domain.history.error.exception;

import kr.co.littleriders.backend.domain.history.error.code.BoardDropHistoryErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.Getter;

@Getter
public final class BoardDropHistoryException extends LittleRidersException {


    BoardDropHistoryException(BoardDropHistoryErrorCode errorCode) {
        super(errorCode);
    }

    public static BoardDropHistoryException from(BoardDropHistoryErrorCode errorCode) {
        return new BoardDropHistoryException(errorCode);
    }

}
