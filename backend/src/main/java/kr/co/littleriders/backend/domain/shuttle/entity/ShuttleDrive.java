package kr.co.littleriders.backend.domain.shuttle.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "shuttle_drive")
@Getter
public class ShuttleDrive {
    @Id
    private long shuttleId;

    private long routeId;

    private long driverId;

    private long teacherId;

    private LocalDateTime time;

    private ShuttleDrive(final long shuttleId, final long routeId, final long driverId, final long teacherId) {
        this.shuttleId = shuttleId;
        this.routeId = routeId;
        this.driverId = driverId;
        this.teacherId = teacherId;
        this.time = LocalDateTime.now();
    }

    public static ShuttleDrive of(final long shuttleId, final long routeId, final long driverId, final long teacherId) {
        return new ShuttleDrive(shuttleId, routeId, driverId, teacherId);
    }

}
