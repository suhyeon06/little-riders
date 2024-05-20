package kr.co.littleriders.backend.application.facade.impl;

import kr.co.littleriders.backend.application.dto.request.RouteRequest;
import kr.co.littleriders.backend.application.dto.request.RouteStationAcademyChildRequest;
import kr.co.littleriders.backend.application.dto.request.RouteStationRequest;
import kr.co.littleriders.backend.application.dto.response.RouteDetailResponse;
import kr.co.littleriders.backend.application.dto.response.RouteResponse;
import kr.co.littleriders.backend.application.facade.RouteFacade;
import kr.co.littleriders.backend.domain.academy.AcademyChildService;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.route.RouteService;
import kr.co.littleriders.backend.domain.route.entity.Route;
import kr.co.littleriders.backend.domain.route.error.code.RouteErrorCode;
import kr.co.littleriders.backend.domain.route.error.exception.RouteException;
import kr.co.littleriders.backend.domain.routeinfo.RouteStationService;
import kr.co.littleriders.backend.domain.routeinfo.entity.ChildBoardDropInfo;
import kr.co.littleriders.backend.domain.routeinfo.entity.RouteStation;
import kr.co.littleriders.backend.domain.station.StationService;
import kr.co.littleriders.backend.domain.station.entity.Station;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class RouteFacadeImpl implements RouteFacade {

    private final RouteService routeService;
    private final AcademyService academyService;
    private final StationService stationService;
    private final RouteStationService routeStationService;
    private final AcademyChildService academyChildService;

    @Override
    @Transactional
    public long createRoute(long academyId, RouteRequest routeRequest) {
        Academy academy = academyService.findById(academyId);

        String name = routeRequest.getName();
        if (routeService.existsByAcademyIdAndName(academyId, name)) {
            throw RouteException.from(RouteErrorCode.DUPLICATE_NAME);
        }

        Route route = routeRequest.toRoute(academy);

        return routeService.save(route);
    }

    @Override
    @Transactional
    public void addRouteStation(long academyId, long routeId, List<RouteStationRequest> requestList) {
        Academy academy = academyService.findById(academyId);
        Route route = routeService.findById(routeId);

        // 해당 경로의 기존 정류장 리스트
        List<RouteStation> existingStations = route.getRouteStationList();

        // 기존 정류장 리스트가 비어있다면 새로운 정류장들을 추가
        if (existingStations == null || existingStations.isEmpty()) {
            List<RouteStation> newRouteStationList = requestList.stream()
                    .map(req -> {
                        Station station = stationService.findById(req.getId());
                        RouteStation newRouteStation = req.toRouteStation(route, academy, station);
                        route.addRouteStation(newRouteStation);
                        return newRouteStation;
                    })
                    .collect(Collectors.toList());
            routeStationService.saveAll(newRouteStationList);
        } else {
            // 기존 정류장 리스트와 새로운 정류장 리스트를 비교하여 업데이트 수행
            Map<Long, RouteStation> existingStationMap = existingStations.stream()
                    .collect(Collectors.toMap(rs -> rs.getStation().getId(), Function.identity())); // 키: id, 값:rs

            for (RouteStationRequest req : requestList) {
                Station station = stationService.findById(req.getId());

                RouteStation existingRouteStation = existingStationMap.remove(station.getId());
                if (existingRouteStation == null) { // 기존에 없던 새로운 정류장인 경우
                    RouteStation newRouteStation = req.toRouteStation(route, academy, station);
                    route.addRouteStation(newRouteStation);
                } else { // 기존에 존재하는 정류장인 경우
                    // 방문 순서가 수정되었다면 업데이트
                    if (!Objects.equals(existingRouteStation.getVisitOrder(), req.getVisitOrder())) {
                        existingRouteStation.updateVisitOrder(req.getVisitOrder());
                    }
                }
            }

            // 새로운 정류장 리스트에 포함되지 않은 기존 정류장들을 삭제
            existingStationMap.values().forEach(route::removeRouteStation);

            routeService.save(route);
        }
    }

    @Override
    @Transactional
    public void addAcademyChildToRouteStation(long academyId, long routeId, List<RouteStationAcademyChildRequest> requestList) {
        Academy academy = academyService.findById(academyId);

        for (RouteStationAcademyChildRequest request : requestList) {
            RouteStation routeStation = routeStationService.findByRouteIdAndStationId(routeId, request.getStationId());

            // 기존의 모든 ChildBoardDropInfo 삭제
            List<ChildBoardDropInfo> existingInfoList = new ArrayList<>(routeStation.getChildBoardInfoList());
            existingInfoList.forEach(routeStation::removeChildBoardDropInfo);

            // request에서 받은 원생 승하차 정보를 저장
            for (Long academyChildId : request.getAcademyChildIdList()) {
                AcademyChild academyChild = academyChildService.findById(academyChildId);
                ChildBoardDropInfo newInfo = request.toChildBoardDropInfo(academyChild, academy, routeStation);
                routeStation.addChildBoardDropInfo(newInfo);
            }

            routeStationService.save(routeStation);
        }
    }

    @Override
    public List<RouteResponse> getAllRoute(long academyId) {
        Academy academy = academyService.findById(academyId);
        List<Route> routeList = routeService.findAllByAcademy(academy);
        return routeList.stream()
                .map(RouteResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public RouteDetailResponse getRoute(long academyId, long routeId) {
        Route route = routeService.findById(routeId);

        if(!Objects.equals(academyId, route.getAcademy().getId())) {
            throw RouteException.from(RouteErrorCode.FORBIDDEN);
        }

        return RouteDetailResponse.from(route);
    }


    @Override
    @Transactional
    public void updateRoute(long academyId, long routeId, RouteRequest routeRequest) {
        Route route = routeService.findById(routeId);

        if(route.getAcademy().getId() != academyId) {
            throw RouteException.from(RouteErrorCode.FORBIDDEN);
        }
        routeService.updateRoute(route, routeRequest);
    }

    @Override
    @Transactional
    public void deleteRoute(long academyId, long routeId) {
        Route route = routeService.findById(routeId);

        if(route.getAcademy().getId() != academyId) {
            throw RouteException.from(RouteErrorCode.FORBIDDEN);
        }
        routeService.deleteRoute(route);
    }

}
