package kr.co.littleriders.backend.domain.routeinfo;

import kr.co.littleriders.backend.domain.routeinfo.entity.RouteStation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface RouteStationService {

    RouteStation findById(long id);

    boolean existsById(long id);

    boolean notExistsById(long id);

    long save(RouteStation routeStation);

    void saveAll(List<RouteStation> routeStationList);

    RouteStation findByRouteIdAndStationId(Long routeId, Long stationId);

}
