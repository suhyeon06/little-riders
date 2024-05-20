package kr.co.littleriders.backend.domain.shuttle;

import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleDrive;

public interface ShuttleDriveService {

    void save(ShuttleDrive shuttleDrive);

    ShuttleDrive findByShuttleId(long shuttleId);

    void delete(ShuttleDrive shuttleDrive);

    boolean existsByShuttleId(Long shuttleId);

    boolean notExistsByShuttleId(long shuttleId);
}
