package kr.co.littleriders.backend.application.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TerminalSignInRequest {



    private String terminalNumber;

    private TerminalSignInRequest(String terminalNumber) {
        this.terminalNumber = terminalNumber;
    }

    public static TerminalSignInRequest of(String terminalNumber) {
        return new TerminalSignInRequest(terminalNumber);
    }
}
