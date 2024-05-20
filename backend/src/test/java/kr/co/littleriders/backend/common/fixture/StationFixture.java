package kr.co.littleriders.backend.common.fixture;


import kr.co.littleriders.backend.application.dto.request.StationRequest;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.station.entity.Station;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)

@AllArgsConstructor
@Getter
public enum StationFixture {

    GANG_NAM("강남역", 37.4979084, 127.0276954),
    UN_JU("언주역", 37.5071434, 127.0340362),
    YEOK_SAM("역삼역", 37.5006407, 127.0370402),
    SUN_REONG("선릉역", 37.5044794, 127.0489385),
    SHIN_NON_HYUN("신논현역", 37.5044369, 127.0246162),

    NON_HYUN("논현역", 37.5109732, 127.0214405),
    HAK_DONG("학동역", 37.5142071, 127.0316544),
    BAN_PO("반포역", 37.5079604, 127.0116558),
    GYO_DAE_1_EXIT("교대역 1번출구", 37.4940609, 127.0156148),
    GYO_DAE_9_EXIT("교대역 9번출구", 37.4932948, 127.0131471);


    private String name;
    private double latitude;
    private double longitude;

    public Station toStation(Academy academy) {
        return Station.of(academy, name, latitude, longitude);
    }

    public StationRequest toStationRequest(){
        return  new StationRequest(name,latitude,longitude);
    }

}
