package kr.co.littleriders.backend.domain.shuttle.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "shuttle_board")
@Getter
public class ShuttleBoard {

    @Id
    private long academyChildId;

    @Indexed
    private long shuttleId;

    @Indexed
    private long academyId;

    private double latitude;

    private double longitude;

    private LocalDateTime time;

    private ShuttleBoard(long academyChildId, long shuttleId, long academyId, double latitude, double longitude) {
        this.academyChildId = academyChildId;
        this.shuttleId = shuttleId;
        this.academyId = academyId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = LocalDateTime.now();

    }

    public static ShuttleBoard of(long academyChildId, long shuttleId, long academyId, double latitude, double longitude) {
        return new ShuttleBoard(academyChildId, shuttleId, academyId, latitude, longitude);
    }
}
