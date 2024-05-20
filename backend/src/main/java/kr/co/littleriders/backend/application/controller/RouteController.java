package kr.co.littleriders.backend.application.controller;

import kr.co.littleriders.backend.application.dto.request.RouteRequest;
import kr.co.littleriders.backend.application.dto.request.RouteStationAcademyChildRequest;
import kr.co.littleriders.backend.application.dto.request.RouteStationRequest;
import kr.co.littleriders.backend.application.dto.response.RouteDetailResponse;
import kr.co.littleriders.backend.application.dto.response.RouteResponse;
import kr.co.littleriders.backend.application.facade.RouteFacade;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/academy/route")
@RequiredArgsConstructor
public class RouteController {
    private final RouteFacade routeFacade;

    // 노선 등록
    @PostMapping
    public ResponseEntity<Long> createRoute(@Auth AuthAcademy authAcademy, @RequestBody RouteRequest routeRequest) {
        long academyId = authAcademy.getId();
        return ResponseEntity.status(HttpStatus.CREATED).body(routeFacade.createRoute(academyId, routeRequest));
    }

    // 노선별 정류장 추가
    @PostMapping("/{route_id}")
    public ResponseEntity<Void> addRouteStation(@Auth AuthAcademy authAcademy, @PathVariable("route_id") long routeId, @RequestBody List<RouteStationRequest> requestList) {
        long academyId = authAcademy.getId();
        routeFacade.addRouteStation(academyId, routeId, requestList);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 노선별 정류장에 원생 추가
    @PostMapping("/{route_id}/child")
    public ResponseEntity<Void> addAcademyChildToRouteStation(@Auth AuthAcademy authAcademy, @PathVariable("route_id") long routeId, @RequestBody List<RouteStationAcademyChildRequest> requestList) {
        long academyId = authAcademy.getId();
        routeFacade.addAcademyChildToRouteStation(academyId, routeId, requestList);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 노선 목록 조회
    @GetMapping
    public ResponseEntity<List<RouteResponse>> getRouteList(@Auth AuthAcademy authAcademy) {
        long academyId = authAcademy.getId();
        return ResponseEntity.ok().body(routeFacade.getAllRoute(academyId));
    }

    // 노선 수정
    @PutMapping("/{route_id}")
    public ResponseEntity<Void> updateStation(@Auth AuthAcademy authAcademy, @PathVariable("route_id") long routeId, @RequestBody RouteRequest routeRequest) {
        long academyId = authAcademy.getId();
        routeFacade.updateRoute(academyId, routeId, routeRequest);
        return ResponseEntity.ok().build();
    }

    // 노선 삭제
    @DeleteMapping("/{route_id}")
    public ResponseEntity<Void> deleteStation(@Auth AuthAcademy authAcademy, @PathVariable("route_id") long routeId) {
        long academyId = authAcademy.getId();
        routeFacade.deleteRoute(academyId, routeId);
        return ResponseEntity.ok().build();
    }


    // 노선 상세 조회
    @GetMapping("/{route_id}")
    public ResponseEntity<RouteDetailResponse> getRoute(@Auth AuthAcademy authAcademy, @PathVariable(name = "route_id") long routeId) {
        long academyId = authAcademy.getId();
        return ResponseEntity.ok().body(routeFacade.getRoute(academyId, routeId));
    }

}