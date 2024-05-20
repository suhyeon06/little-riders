package kr.co.littleriders.backend.domain.terminal;

import kr.co.littleriders.backend.domain.terminal.entity.ShuttleTerminalAttach;

public interface ShuttleTerminalAttachService {

    ShuttleTerminalAttach findById(long id);

    boolean existsById(long id);

    boolean notExistsById(long id);

    long save(ShuttleTerminalAttach shuttleTerminalAttach);
}
