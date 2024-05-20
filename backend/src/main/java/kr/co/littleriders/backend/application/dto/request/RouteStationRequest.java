package kr.co.littleriders.backend.application.dto.request;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.route.entity.Route;
import kr.co.littleriders.backend.domain.routeinfo.entity.RouteStation;
import kr.co.littleriders.backend.domain.station.entity.Station;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RouteStationRequest {
    private long id;
    private int visitOrder;

    public RouteStation toRouteStation(Route route, Academy academy, Station station) {
        return RouteStation.of(route, academy, station, this.visitOrder);
    }
}
