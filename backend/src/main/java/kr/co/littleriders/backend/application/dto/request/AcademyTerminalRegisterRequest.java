package kr.co.littleriders.backend.application.dto.request;


import jakarta.validation.constraints.NotBlank;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.terminal.entity.Terminal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AcademyTerminalRegisterRequest {

    @NotBlank
    private String terminalNumber;


    public Terminal toTerminal(Academy academy){
        return Terminal.of(academy,terminalNumber);
    }

}
