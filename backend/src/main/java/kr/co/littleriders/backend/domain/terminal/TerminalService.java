package kr.co.littleriders.backend.domain.terminal;

import kr.co.littleriders.backend.domain.terminal.entity.Terminal;

public interface TerminalService {

    Terminal findById(long id);

    Terminal findByTerminalNumber(String terminalNumber);
    
    boolean existsById(long id);

    boolean notExistsById(long id);
    
    long save(Terminal terminal);

    boolean existsByTerminalNumber(String terminalNumber);
}
