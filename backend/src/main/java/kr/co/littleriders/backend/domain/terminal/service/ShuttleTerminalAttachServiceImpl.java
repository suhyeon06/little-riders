package kr.co.littleriders.backend.domain.terminal.service;

import kr.co.littleriders.backend.domain.terminal.ShuttleTerminalAttachService;
import kr.co.littleriders.backend.domain.terminal.entity.ShuttleTerminalAttach;
import kr.co.littleriders.backend.domain.terminal.error.code.ShuttleTerminalAttachErrorCode;
import kr.co.littleriders.backend.domain.terminal.error.exception.ShuttleTerminalAttachException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class ShuttleTerminalAttachServiceImpl implements ShuttleTerminalAttachService {

    private final ShuttleTerminalAttachRepository shuttleTerminalAttachRepository;

    @Override
    public ShuttleTerminalAttach findById(final long id) {
        return shuttleTerminalAttachRepository.findById(id)
                .orElseThrow(
                        () -> ShuttleTerminalAttachException.from(ShuttleTerminalAttachErrorCode.NOT_FOUND)
                );
    }

    @Override
    public boolean existsById(final long id) {
        return shuttleTerminalAttachRepository.existsById(id);
    }

    @Override
    public boolean notExistsById(final long id) {
        return !shuttleTerminalAttachRepository.existsById(id);
    }

    @Override
    @Transactional
    public long save(final ShuttleTerminalAttach shuttleTerminalAttach) {
        return shuttleTerminalAttachRepository.save(shuttleTerminalAttach).getId();
    }


}
