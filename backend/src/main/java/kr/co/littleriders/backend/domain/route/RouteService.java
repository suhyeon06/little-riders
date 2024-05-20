package kr.co.littleriders.backend.domain.route;

import kr.co.littleriders.backend.application.dto.request.RouteRequest;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.route.entity.Route;

import java.util.List;

public interface RouteService {
    Route findById(long id);

    boolean existsById(long id);

    boolean notExistsById(long id);

    boolean existsByAcademyIdAndName(long academyId, String name);

    long save(Route route);

    List<Route> findAllByAcademy(Academy academy);

    void updateRoute(Route route, RouteRequest routeRequest);

    void deleteRoute(Route route);
}
