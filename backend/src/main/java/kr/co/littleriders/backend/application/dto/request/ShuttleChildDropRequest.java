package kr.co.littleriders.backend.application.dto.request;

import kr.co.littleriders.backend.domain.shuttle.entity.DriveUniqueKey;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleDrop;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShuttleChildDropRequest {
    private String beaconUUID;
    private double latitude;
    private double longitude;

    public ShuttleDrop toShuttleDrop(DriveUniqueKey driveUniqueKey, long academyId) {
        return ShuttleDrop.of(
                driveUniqueKey.getAcademyChildId(),
                driveUniqueKey.getShuttleId(),
                academyId,
                this.latitude,
                this.longitude);
    }
}