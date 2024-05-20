package kr.co.littleriders.backend.domain.terminal.service;

import kr.co.littleriders.backend.domain.terminal.TerminalService;
import kr.co.littleriders.backend.domain.terminal.entity.Terminal;
import kr.co.littleriders.backend.domain.terminal.error.code.TerminalErrorCode;
import kr.co.littleriders.backend.domain.terminal.error.exception.TerminalException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class TerminalServiceImpl implements TerminalService {

    private final TerminalRepository terminalRepository;

    @Override
    public Terminal findById(final long id) {
        return terminalRepository.findById(id).orElseThrow(
                () -> TerminalException.from(TerminalErrorCode.NOT_FOUND)
        );
    }

    @Override
    public Terminal findByTerminalNumber(String terminalNumber) {
        return terminalRepository.findByTerminalNumber(terminalNumber).orElseThrow(
                () -> TerminalException.from(TerminalErrorCode.NOT_FOUND)
        );

    }

    @Override
    public boolean existsById(final long id) {
        return terminalRepository.existsById(id);
    }

    @Override
    public boolean notExistsById(final long id) {
        return !terminalRepository.existsById(id);
    }

    @Override
    @Transactional
    public long save(final Terminal terminal) {
        return terminalRepository.save(terminal).getId();
    }

    @Override
    public boolean existsByTerminalNumber(String terminalNumber) {
        return terminalRepository.existsByTerminalNumber(terminalNumber);
    }
}
