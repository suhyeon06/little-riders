package kr.co.littleriders.backend.domain.routeinfo.service;

import kr.co.littleriders.backend.domain.routeinfo.RouteStationService;
import kr.co.littleriders.backend.domain.routeinfo.entity.RouteStation;
import kr.co.littleriders.backend.domain.routeinfo.error.code.RouteStationErrorCode;
import kr.co.littleriders.backend.domain.routeinfo.error.exception.RouteStationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class RouteStationServiceImpl implements RouteStationService {
    private final RouteStationRepository routeStationRepository;

    @Override
    public RouteStation findById(final long id) {
        return routeStationRepository.findById(id).orElseThrow(
                () -> RouteStationException.from(RouteStationErrorCode.NOT_FOUND)
        );
    }

    @Override
    public boolean existsById(final long id) {
        return routeStationRepository.existsById(id);
    }

    @Override
    public boolean notExistsById(final long id) {
        return !routeStationRepository.existsById(id);
    }

    @Override
    @Transactional
    public long save(RouteStation routeStation) {
        return routeStationRepository.save(routeStation).getId();
    }

    @Override
    @Transactional
    public void saveAll(List<RouteStation> routeStationList) {
        routeStationRepository.saveAll(routeStationList);
    }

    @Override
    public RouteStation findByRouteIdAndStationId(Long routeId, Long stationId) {
        return routeStationRepository.findByRouteIdAndStationId(routeId, stationId).orElseThrow(
                () -> RouteStationException.from(RouteStationErrorCode.NOT_FOUND)
        );
    }

}
