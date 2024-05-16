package kr.co.littleriders.backend.application.facade;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import kr.co.littleriders.backend.application.dto.response.ShuttleDailyHistoryResponse;
import kr.co.littleriders.backend.application.dto.response.ShuttleDetailHistoryResponse;

public interface HistoryFacade {

	List<LocalDateTime> readShuttleDateList(Long shuttleId, Long academyId);

	List<ShuttleDailyHistoryResponse> readShuttleDailyHistory(Long academyId, Long shuttleId, LocalDate date);

	ShuttleDetailHistoryResponse readShuttleDetailHistory(String historyId);
}
