package kr.co.littleriders.backend.global.auth.dto;

import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.terminal.entity.Terminal;
import lombok.Getter;

@Getter
public class AuthTerminal implements AuthDTO {

    private final long terminalId;
    private final long shuttleId;

    private AuthTerminal(long terminalId, long shuttleId) {
        this.terminalId = terminalId;
        this.shuttleId = shuttleId;
    }
    public static AuthTerminal of (Terminal terminal, Shuttle shuttle){
        return new AuthTerminal(terminal.getId(),shuttle.getId());
    }
}
