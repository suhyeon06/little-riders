package kr.co.littleriders.backend.application.dto.request;

import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShuttleLocationRequest {
    private double latitude;
    private double longitude;
    private int speed;

    public ShuttleLocation toShuttleLocation(long shuttleId) {
        return ShuttleLocation.of(shuttleId, this.latitude, this.longitude, this.speed);
    }

}
