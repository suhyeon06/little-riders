package kr.co.littleriders.backend.application.dto.request;

import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleDrive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShuttleStartRequest {
    private long routeId;
    private long driverId;
    private long teacherId;

    public ShuttleDrive toShuttleDrive(long shuttleId) {
        return ShuttleDrive.of(shuttleId, this.routeId, this.driverId, this.teacherId);
    }

}
