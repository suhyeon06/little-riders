package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.domain.station.entity.Station;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StationResponse {
    private long id;
    private String name;
    private double latitude;
    private double longitude;

    public static StationResponse from(Station station) {
        long id = station.getId();
        String name = station.getName();
        double latitude = station.getLatitude();
        double longitude = station.getLongitude();

        return new StationResponse(id, name, latitude, longitude);
    }
}
