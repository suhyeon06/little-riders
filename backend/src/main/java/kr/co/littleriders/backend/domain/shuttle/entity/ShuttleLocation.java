package kr.co.littleriders.backend.domain.shuttle.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "shuttle_location")
@Getter
public class ShuttleLocation {
    @Id
    private String id;

    @Indexed
    private long shuttleId;

    private double latitude;

    private double longitude;

    private int speed;

    private LocalDateTime time;

    private ShuttleLocation(final long shuttleId, double latitude, double longitude, int speed) {
        this.shuttleId = shuttleId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
        this.time = LocalDateTime.now();
    }

    public static ShuttleLocation of(final long shuttleId, double latitude, double longitude, int speed) {
        return new ShuttleLocation(shuttleId, latitude, longitude, speed);
    }

}
