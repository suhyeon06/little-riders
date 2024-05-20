package kr.co.littleriders.backend.application.facade.impl;

import kr.co.littleriders.backend.application.dto.request.AcademyTerminalRegisterRequest;
import kr.co.littleriders.backend.common.fixture.AcademyFixture;
import kr.co.littleriders.backend.common.fixture.TerminalFixture;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.terminal.TerminalService;
import kr.co.littleriders.backend.domain.terminal.error.exception.TerminalException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@SpringBootTest
@Transactional
class AcademyTerminalFacadeImplTest {

    @Autowired
    AcademyTerminalFacadeImpl academyTerminalFacade;

    @MockBean
    TerminalService terminalService;

    @Autowired
    AcademyService academyService;

    @Nested
    @DisplayName("registerTerminal 테스트")
    class registerTerminal {

        @Test
        @DisplayName("성공")
        void whenSuccess() {

            //given

            Academy academy = AcademyFixture.BOXING.toAcademy();
            academyService.save(academy);


            TerminalFixture terminalFixture = TerminalFixture.T;
            AcademyTerminalRegisterRequest academyTerminalRegisterRequest = terminalFixture.toAcademyTerminalRegisterRequest();
            given(terminalService.existsByTerminalNumber(any(String.class))).willReturn(false);

            assertDoesNotThrow(() -> {
                academyTerminalFacade.registerTerminal(academy.getId(), academyTerminalRegisterRequest);
            });

        }

        @Test
        @DisplayName("실패 이미 다른데서 등록된 경우")
        void whenFailTerminalAlreadyExists() {

            Academy academy = AcademyFixture.BOXING.toAcademy();

            academyService.save(academy);

            TerminalFixture terminalFixture = TerminalFixture.T;
            AcademyTerminalRegisterRequest academyTerminalRegisterRequest = terminalFixture.toAcademyTerminalRegisterRequest();
            given(terminalService.existsByTerminalNumber(any(String.class))).willReturn(true);

            assertThrows(TerminalException.class,
                    () -> academyTerminalFacade.registerTerminal(academy.getId(), academyTerminalRegisterRequest)
            );

        }


    }


}