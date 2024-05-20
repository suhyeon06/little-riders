package kr.co.littleriders.backend.domain.routeinfo.service;

import kr.co.littleriders.backend.domain.routeinfo.entity.RouteStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
interface RouteStationRepository extends JpaRepository<RouteStation,Long> {
    Optional<RouteStation> findByRouteIdAndStationId(Long routeId, Long stationId);
}
