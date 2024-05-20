package kr.co.littleriders.backend.domain.route.service;

import kr.co.littleriders.backend.application.dto.request.RouteRequest;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.route.RouteService;
import kr.co.littleriders.backend.domain.route.entity.Route;
import kr.co.littleriders.backend.domain.route.error.code.RouteErrorCode;
import kr.co.littleriders.backend.domain.route.error.exception.RouteException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    @Override
    public Route findById(long id) {
        return routeRepository.findById(id).orElseThrow(
                () -> RouteException.from(RouteErrorCode.NOT_FOUND)
        );
    }

    @Override
    public boolean existsById(final long id) {
        return routeRepository.existsById(id);
    }

    @Override
    public boolean notExistsById(final long id) {
        return !routeRepository.existsById(id);
    }

    @Override
    public boolean existsByAcademyIdAndName(long academyId, String name) {
        return routeRepository.existsByAcademyIdAndName(academyId, name);
    }

    @Override
    @Transactional
    public long save(Route route) {
        return routeRepository.save(route).getId();
    }

    @Override
    public List<Route> findAllByAcademy(Academy academy) {
        return routeRepository.findAllByAcademy(academy);
    }

    @Override
    @Transactional
    public void updateRoute(Route route, RouteRequest routeRequest) {
        route.update(routeRequest);
    }

    @Override
    @Transactional
    public void deleteRoute(Route route) {
        routeRepository.delete(route);
    }


}
