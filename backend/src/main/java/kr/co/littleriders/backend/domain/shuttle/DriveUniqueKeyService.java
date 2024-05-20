package kr.co.littleriders.backend.domain.shuttle;

import kr.co.littleriders.backend.domain.shuttle.entity.DriveUniqueKey;

import java.util.List;

public interface DriveUniqueKeyService {

    void save(DriveUniqueKey driveUniqueKey);

    List<DriveUniqueKey> findByShuttleId(long shuttleId);

    void delete(DriveUniqueKey driveUniqueKey);

    void deleteAllByShuttleId(long shuttleId);

    DriveUniqueKey findByUuid(String uuid);

    DriveUniqueKey findByAcademyChildId(long academyChildId);

}
