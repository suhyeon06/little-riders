package kr.co.littleriders.backend.domain.station;

import kr.co.littleriders.backend.application.dto.request.StationRequest;
import kr.co.littleriders.backend.domain.station.entity.Station;

import java.util.List;

public interface StationService {

    Station findById(long id);

    boolean existsById(long id);

    boolean notExistsById(long id);

    boolean existsByAcademyIdAndName(long academyId, String name);

    long save(Station station);

    List<Station> findAllByAcademyIdAndName(long academyId, String name);

    void updateStation(Station station, StationRequest stationRequest);

    void deleteStation(Station station);
}
