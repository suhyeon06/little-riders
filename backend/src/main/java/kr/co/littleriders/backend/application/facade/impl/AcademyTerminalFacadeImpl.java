package kr.co.littleriders.backend.application.facade.impl;


import kr.co.littleriders.backend.application.dto.request.AcademyTerminalRegisterRequest;
import kr.co.littleriders.backend.application.facade.AcademyTerminalFacade;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.terminal.TerminalService;
import kr.co.littleriders.backend.domain.terminal.entity.Terminal;
import kr.co.littleriders.backend.domain.terminal.error.code.TerminalErrorCode;
import kr.co.littleriders.backend.domain.terminal.error.exception.TerminalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class AcademyTerminalFacadeImpl implements AcademyTerminalFacade {

    private final TerminalService terminalService;
    private final AcademyService academyService;


    @Override
    @Transactional
    public void registerTerminal(long academyId, AcademyTerminalRegisterRequest academyTerminalRegisterRequest) {

        String terminalNumber = academyTerminalRegisterRequest.getTerminalNumber();
        if(terminalService.existsByTerminalNumber(terminalNumber)){
            throw TerminalException.from(TerminalErrorCode.ALREADY_REGISTERED);
        }
        Academy academy = academyService.findById(academyId);
        Terminal terminal = academyTerminalRegisterRequest.toTerminal(academy);
        terminalService.save(terminal);
    }
}
