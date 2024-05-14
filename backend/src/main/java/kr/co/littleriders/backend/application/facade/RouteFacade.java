package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.RouteRequest;
import kr.co.littleriders.backend.application.dto.request.RouteStationAcademyChildRequest;
import kr.co.littleriders.backend.application.dto.request.RouteStationRequest;
import kr.co.littleriders.backend.application.dto.response.RouteDetailResponse;
import kr.co.littleriders.backend.application.dto.response.RouteResponse;

import java.util.List;

public interface RouteFacade {
    long createRoute(long academyId, RouteRequest routeRequest);

    void addRouteStation(long academyId, long routeId, List<RouteStationRequest> requestList);

    void addAcademyChildToRouteStation(long academyId, long routeId, List<RouteStationAcademyChildRequest> requestList);

    List<RouteResponse> getAllRoute(long academyId);

    RouteDetailResponse getRoute(long academyId, long routeId);

    void updateRoute(long academyId, long routeId, RouteRequest routeRequest);

    void deleteRoute(long academyId, long routeId);
}
