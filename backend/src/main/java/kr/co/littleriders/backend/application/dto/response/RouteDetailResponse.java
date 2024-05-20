package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.domain.route.entity.Route;
import kr.co.littleriders.backend.domain.routeinfo.entity.RouteStation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RouteDetailResponse {
    private Long id;
    private String name;
    private String type;
    private List<StationInfo> stationList;

    public static RouteDetailResponse from(Route route) {
        RouteDetailResponse response = new RouteDetailResponse();
        response.id = route.getId();
        response.name = route.getName();
        response.type = route.getType();
        response.stationList = route.getRouteStationList().stream()
                .sorted(Comparator.comparingInt(RouteStation::getVisitOrder))
                .map(routeStation -> new StationInfo(
                        routeStation.getStation().getId(),
                        routeStation.getStation().getName(),
                        routeStation.getStation().getLatitude(),
                        routeStation.getStation().getLongitude(),
                        routeStation.getVisitOrder(),
                        routeStation.getChildBoardInfoList().stream()
                                .map(info -> new StationInfo.ChildInfo(info.getAcademyChild().getId(), info.getAcademyChild().getName(),info.getAcademyChild().getPhoneNumber(),info.getAcademyChild().getImagePath()))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
        return response;
    }

    @Getter
    @AllArgsConstructor
    public static class StationInfo {
        private Long id;
        private String name;
        private Double latitude;
        private Double longitude;
        private Integer visitOrder;
        private List<ChildInfo> academyChildList;

        @Getter
        @AllArgsConstructor
        public static class ChildInfo {
            private Long academyChildId;
            private String name;
            private String phoneNumber;
            private String image;
        }
    }
}
