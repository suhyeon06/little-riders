package kr.co.littleriders.backend.domain.shuttle;

import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleLocation;

import java.util.List;

public interface ShuttleLocationService {

    List<ShuttleLocation> findByShuttleId(long shuttleId);

    void save(ShuttleLocation shuttleLocation);

    void delete(ShuttleLocation shuttleLocation);

    void deleteAllByShuttleId(long shuttleId);
}
