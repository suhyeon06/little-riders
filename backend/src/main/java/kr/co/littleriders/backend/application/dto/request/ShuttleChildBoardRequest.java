package kr.co.littleriders.backend.application.dto.request;

import kr.co.littleriders.backend.domain.shuttle.entity.DriveUniqueKey;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShuttleChildBoardRequest {
    private String beaconUUID;
    private double latitude;
    private double longitude;

    public ShuttleBoard toShuttleBoard(DriveUniqueKey driveUniqueKey, long academyId) {
        return ShuttleBoard.of(
                driveUniqueKey.getAcademyChildId(),
                driveUniqueKey.getShuttleId(),
                academyId,
                this.latitude,
                this.longitude);
    }
}