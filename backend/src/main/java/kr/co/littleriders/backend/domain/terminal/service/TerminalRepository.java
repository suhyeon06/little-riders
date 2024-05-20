package kr.co.littleriders.backend.domain.terminal.service;

import kr.co.littleriders.backend.domain.terminal.entity.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface TerminalRepository extends JpaRepository<Terminal, Long> {

    Optional<Terminal> findByTerminalNumber(String terminalNumber);

    boolean existsByTerminalNumber(String terminalNumber);
}
