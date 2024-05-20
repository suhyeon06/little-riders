package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.response.ShuttleDailyHistoryResponse;
import kr.co.littleriders.backend.application.dto.response.ShuttleDetailHistoryResponse;
import kr.co.littleriders.backend.application.dto.response.ShuttleHistoryDateResponse;

import java.util.List;

public interface HistoryFacade {

	List<ShuttleHistoryDateResponse> readShuttleDateList(Long shuttleId, Long academyId);

	List<ShuttleDailyHistoryResponse> readShuttleDailyHistory(Long academyId, Long shuttleId, int year, int month, int day);

	ShuttleDetailHistoryResponse readShuttleDetailHistory(String historyId);
}
