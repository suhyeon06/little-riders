package kr.co.littleriders.backend.domain.terminal.service;

import kr.co.littleriders.backend.domain.terminal.entity.ShuttleTerminalAttach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ShuttleTerminalAttachRepository extends JpaRepository<ShuttleTerminalAttach, Long> {
}
