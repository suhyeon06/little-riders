package kr.co.littleriders.backend.domain.terminal.error.exception;

import kr.co.littleriders.backend.domain.terminal.error.code.TerminalErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.Getter;

@Getter
public final class TerminalException extends LittleRidersException {

    TerminalException(TerminalErrorCode errorCode) {
        super(errorCode);
    }

    public static TerminalException from(TerminalErrorCode errorCode) {
        return new TerminalException(errorCode);
    }

}
