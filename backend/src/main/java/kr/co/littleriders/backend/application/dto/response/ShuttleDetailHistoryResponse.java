package kr.co.littleriders.backend.application.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import kr.co.littleriders.backend.domain.history.entity.ShuttleDriveHistory;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShuttleDetailHistoryResponse {

	private String id;

	private WorkerInfo teacher;

	private WorkerInfo driver;

	private ShuttleInfo shuttle;

	private List<Location> locationList;

	private LocalDateTime start;

	private LocalDateTime end;

	private ShuttleDetailHistoryResponse(String documentId, WorkerInfo teacher, WorkerInfo driver, ShuttleInfo shuttle, List<Location> locationList, LocalDateTime start, LocalDateTime end) {
		this.id = documentId;
		this.teacher = teacher;
		this.driver = driver;
		this.shuttle = shuttle;
		this.locationList = locationList;
		this.start = start;
		this.end = end;
	}

	@Getter
	@NoArgsConstructor
	public static class Location {
		private Double latitude;
		private Double longitude;
		private Integer speed;
		private LocalDateTime time;

		private Location(double latitude, double longitude, LocalDateTime time, int speed) {
			this.latitude = latitude;
			this.longitude = longitude;
			this.time = time;
			this.speed = speed;
		}
	}

	@Getter
	@NoArgsConstructor
	public static class WorkerInfo {
		private Long id;
		private String name;
		private String phoneNumber;

		private WorkerInfo(long id, String name, String phoneNumber) {
			this.id = id;
			this.name = name;
			this.phoneNumber = phoneNumber;
		}
	}

	@Getter
	@NoArgsConstructor
	public static class ShuttleInfo {
		private Long id;
		private String name;
		private String type;
		private String licenseNumber;

		public ShuttleInfo(long id, String name, String type, String licenseNumber) {
			this.id = id;
			this.name = name;
			this.type = type;
			this.licenseNumber = licenseNumber;
		}
	}

	public static ShuttleDetailHistoryResponse from(ShuttleDriveHistory shuttleDriveHistory) {

		ShuttleDriveHistory.TeacherInShuttleDriveHistory teacher = shuttleDriveHistory.getTeacher();
		ShuttleDriveHistory.DriverInShuttleDriveHistory driver = shuttleDriveHistory.getDriver();
		ShuttleDriveHistory.ShuttleInShuttleDriveHistory shuttle = shuttleDriveHistory.getShuttle();

		return new ShuttleDetailHistoryResponse(shuttleDriveHistory.getDocumentId(),
			new WorkerInfo(teacher.getId(), teacher.getName(), teacher.getPhoneNumber()),
			new WorkerInfo(driver.getId(), driver.getName(), driver.getPhoneNumber()),
			new ShuttleInfo(shuttle.getId(), shuttle.getName(), shuttle.getType(), shuttle.getLicenseNumber()),
			shuttleDriveHistory.getLocationList().stream()
				.map(location -> new Location(location.getLatitude(), location.getLongitude(), location.getTime(), location.getSpeed()))
				.collect(Collectors.toList()),
			shuttleDriveHistory.getStart(), shuttleDriveHistory.getEnd());
	}

}
