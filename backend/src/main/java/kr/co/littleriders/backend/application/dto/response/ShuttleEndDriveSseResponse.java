package kr.co.littleriders.backend.application.dto.response;

import lombok.Getter;

@Getter
public class ShuttleEndDriveSseResponse {
    private final long shuttleId;

    private ShuttleEndDriveSseResponse(long shuttleId) {
        this.shuttleId = shuttleId;
    }
    public static ShuttleEndDriveSseResponse from(long shuttleId){
        return new ShuttleEndDriveSseResponse(shuttleId);
    }
}
