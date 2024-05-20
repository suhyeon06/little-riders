package kr.co.littleriders.backend.domain.history;

import kr.co.littleriders.backend.domain.history.entity.ShuttleBoardDropHistory;

public interface ShuttleBoardDropHistoryService {

	String save(ShuttleBoardDropHistory shuttleBoardDropHistory);

	ShuttleBoardDropHistory findByDriveUniqueKeyUuid(String driveUniqueKeyUuid);

	boolean existsByDriveUniqueKeyUuid(String driveUniqueKeyUuid);
}
