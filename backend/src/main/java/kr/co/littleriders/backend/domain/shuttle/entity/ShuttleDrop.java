package kr.co.littleriders.backend.domain.shuttle.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "shuttle_drop")
@Getter
public class ShuttleDrop {

    @Id
    private long academyChildId;

    @Indexed
    private long shuttleId;

    @Indexed
    private long academyId;

    private double latitude;

    private double longitude;

    private LocalDateTime time;

    private ShuttleDrop(long academyChildId, long shuttleId, long academyId, double latitude, double longitude) {
        this.academyChildId = academyChildId;
        this.shuttleId = shuttleId;
        this.academyId = academyId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = LocalDateTime.now();

    }

    public static ShuttleDrop of(long academyChildId, long shuttleId, long academyId, double latitude, double longitude) {
        return new ShuttleDrop(academyChildId, shuttleId, academyId, latitude, longitude);
    }
}
