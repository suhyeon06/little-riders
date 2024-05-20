package kr.co.littleriders.backend.domain.station.service;

import kr.co.littleriders.backend.application.dto.request.StationRequest;
import kr.co.littleriders.backend.domain.station.StationService;
import kr.co.littleriders.backend.domain.station.entity.Station;
import kr.co.littleriders.backend.domain.station.error.code.StationErrorCode;
import kr.co.littleriders.backend.domain.station.error.exception.StationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;

    @Override
    public Station findById(long id) {
        return stationRepository.findById(id).orElseThrow(() -> StationException.from(StationErrorCode.NOT_FOUND));
    }

    @Override
    public boolean existsById(long id) {
        return stationRepository.existsById(id);
    }

    @Override
    public boolean notExistsById(long id) {
        return !stationRepository.existsById(id);
    }

    @Override
    public boolean existsByAcademyIdAndName(long academyId, String name) {
        return stationRepository.existsByAcademyIdAndName(academyId, name);
    }

    @Override
    @Transactional
    public long save(Station station) {
        return stationRepository.save(station).getId();
    }

    @Override
    public List<Station> findAllByAcademyIdAndName(long academyId, String name) {
        return stationRepository.findAllByAcademyIdAndNameContaining(academyId, name);
    }

    @Override
    @Transactional
    public void updateStation(Station station, StationRequest stationRequest) {
        station.update(stationRequest);
    }

    @Override
    @Transactional
    public void deleteStation(Station station) {
        stationRepository.delete(station);
    }

}
