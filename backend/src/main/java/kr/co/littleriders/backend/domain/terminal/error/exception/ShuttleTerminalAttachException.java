package kr.co.littleriders.backend.domain.terminal.error.exception;


import kr.co.littleriders.backend.domain.terminal.error.code.ShuttleTerminalAttachErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.Getter;

@Getter
public final class ShuttleTerminalAttachException extends LittleRidersException {

    ShuttleTerminalAttachException(ShuttleTerminalAttachErrorCode errorCode) {
        super(errorCode);
    }

    public static ShuttleTerminalAttachException from(ShuttleTerminalAttachErrorCode errorCode) {
        return new ShuttleTerminalAttachException(errorCode);
    }

}
