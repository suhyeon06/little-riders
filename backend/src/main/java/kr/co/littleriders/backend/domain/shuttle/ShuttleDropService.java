package kr.co.littleriders.backend.domain.shuttle;

import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleDrop;

import java.util.List;

public interface ShuttleDropService {

	List<ShuttleDrop> findByShuttleId(long shuttleId);

	void deleteAllByShuttleId(long shuttleId);

	void delete(ShuttleDrop shuttleDrop);

	void save(ShuttleDrop shuttleDrop);
}
