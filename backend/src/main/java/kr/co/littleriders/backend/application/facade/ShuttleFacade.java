package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.*;
import kr.co.littleriders.backend.application.dto.response.*;
import kr.co.littleriders.backend.global.auth.dto.AuthTerminal;

import java.util.List;

public interface ShuttleFacade {
    List<RouteResponse> getRouteList(AuthTerminal authTerminal);

    List<RouteDetailResponse> getRouteListWithStation(AuthTerminal authTerminal);

    RouteDetailResponse getRoute(AuthTerminal authTerminal, long routeId);

    void startDrive(AuthTerminal authTerminal, ShuttleStartRequest startRequest);

    void endDrive(long shuttleId);

    ShuttleChildBoardResponse recordChildBoard(AuthTerminal authTerminal, ShuttleChildBoardRequest boardRequest);

    ShuttleChildDropResponse recordChildDrop(AuthTerminal authTerminal, ShuttleChildDropRequest dropRequest);

    void uploadLocation(AuthTerminal authTerminal, ShuttleLocationRequest locationRequest);

    DriverInfoResponse getDriverInfoByCardNumber(AuthTerminal authTerminal, String cardNumber);

    TeacherInfoResponse getTeacherInfoByCardNumber(AuthTerminal authTerminal, String cardNumber);

    ShuttleInfoResponse getShuttleInfoByShuttleId(long shuttleId);
}


