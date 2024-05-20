package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.application.dto.request.ShuttleLocationRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShuttleLocationResponse {

    private long shuttleId;
    private double latitude;
    private double longitude;
    private int speed;



    public static ShuttleLocationResponse of(long shuttleId, ShuttleLocationRequest shuttleLocationRequest) {
        return new ShuttleLocationResponse(shuttleId,shuttleLocationRequest.getLatitude(),shuttleLocationRequest.getLongitude(), shuttleLocationRequest.getSpeed());
    }
}
