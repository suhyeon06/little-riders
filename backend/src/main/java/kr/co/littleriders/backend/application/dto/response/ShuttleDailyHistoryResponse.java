package kr.co.littleriders.backend.application.dto.response;

import java.time.LocalTime;

import kr.co.littleriders.backend.domain.history.entity.ShuttleDriveHistory;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShuttleDailyHistoryResponse {

	private String id;

	private String routeName;

	private LocalTime startTime;

	private LocalTime endTime;

	private ShuttleDailyHistoryResponse(String documentId, String routeName, LocalTime startTime, LocalTime endTime) {
		this.id = documentId;
		this.routeName = routeName;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public static ShuttleDailyHistoryResponse from(ShuttleDriveHistory shuttleDriveHistory) {
		return new ShuttleDailyHistoryResponse(shuttleDriveHistory.getDocumentId(),
											shuttleDriveHistory.getRouteName(),
											shuttleDriveHistory.getStart().toLocalTime(),
											shuttleDriveHistory.getEnd().toLocalTime());
	}
}
