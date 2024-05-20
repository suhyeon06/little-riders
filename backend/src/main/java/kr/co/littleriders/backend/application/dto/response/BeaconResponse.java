package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.domain.beacon.entity.Beacon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BeaconResponse {

	private Long id;

	private String uuid;

	public static BeaconResponse from(Beacon beacon) {
		return new BeaconResponse(beacon.getId(), beacon.getUuid());
	}
}
