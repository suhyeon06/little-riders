package kr.co.littleriders.backend.domain.shuttle.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "drive_unique_key")
@Getter
public class DriveUniqueKey {
    @Id
    private String uuid;

    @Indexed
    private long shuttleId;

    @Indexed
    private long academyChildId;

    private DriveUniqueKey(final String uuid, final long shuttleId, final long academyChildId) {
        this.uuid = uuid;
        this.shuttleId = shuttleId;
        this.academyChildId = academyChildId;
    }

    public static DriveUniqueKey of(final String uuid, final long shuttleId, final long academyChildId) {
        return new DriveUniqueKey(uuid, shuttleId, academyChildId);
    }

}
