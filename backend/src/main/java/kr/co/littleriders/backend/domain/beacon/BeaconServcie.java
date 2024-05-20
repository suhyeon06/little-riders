package kr.co.littleriders.backend.domain.beacon;

import java.util.List;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.beacon.entity.Beacon;

public interface BeaconServcie {
	List<Beacon> findByAcademy(Academy academy);

	Beacon findById(Long beaconId);

	void save(Beacon beacon);

	Beacon findByUuid(String uuid);
}
