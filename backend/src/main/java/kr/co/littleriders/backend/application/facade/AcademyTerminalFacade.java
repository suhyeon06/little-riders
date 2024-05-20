package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.AcademyTerminalRegisterRequest;

public interface AcademyTerminalFacade {
    void registerTerminal(long academyId, AcademyTerminalRegisterRequest academyTerminalRegisterRequest);
}
