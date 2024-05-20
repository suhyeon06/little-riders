package kr.co.littleriders.backend.domain.history;

import kr.co.littleriders.backend.domain.history.entity.ShuttleDriveHistory;

import java.time.LocalDateTime;
import java.util.List;

public interface ShuttleDriveHistoryService {

    ShuttleDriveHistory findById(String id);

    String save(ShuttleDriveHistory shuttleDriveHistory);

    List<ShuttleDriveHistory> findByShuttleIdAndStartAt(long shuttleId, int year, int month, int day);

    List<LocalDateTime> findDistinctYearAndMonthAndDayListByShuttleId(long shuttleId);
}
