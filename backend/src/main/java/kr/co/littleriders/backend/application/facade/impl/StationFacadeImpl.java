package kr.co.littleriders.backend.application.facade.impl;

import kr.co.littleriders.backend.application.dto.request.StationRequest;
import kr.co.littleriders.backend.application.dto.response.StationResponse;
import kr.co.littleriders.backend.application.facade.StationFacade;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.station.StationService;
import kr.co.littleriders.backend.domain.station.entity.Station;
import kr.co.littleriders.backend.domain.station.error.code.StationErrorCode;
import kr.co.littleriders.backend.domain.station.error.exception.StationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class StationFacadeImpl implements StationFacade {

    private final StationService stationService;
    private final AcademyService academyService;

    @Override
    @Transactional
    public void createStation(long academyId, StationRequest stationRequest) {
        Academy academy = academyService.findById(academyId);

        String name = stationRequest.getName();
        if (stationService.existsByAcademyIdAndName(academyId, name)) {
            throw StationException.from(StationErrorCode.DUPLICATE_NAME);
        }
        Station station = stationRequest.toStation(academy);
        stationService.save(station);
    }

    @Override
    @Transactional
    public List<StationResponse> searchByName(String name, long academyId) {
        List<Station> stationList = stationService.findAllByAcademyIdAndName(academyId, name);
        return stationList.stream()
                .map(StationResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateStation(long academyId, long stationId, StationRequest stationRequest) {
        Station station = stationService.findById(stationId);

        if(station.getAcademy().getId() != academyId) {
            throw StationException.from(StationErrorCode.FORBIDDEN);
        }
        stationService.updateStation(station, stationRequest);
    }

    @Override
    @Transactional
    public void deleteStation(long academyId, long stationId) {
        Station station = stationService.findById(stationId);

        if(station.getAcademy().getId() != academyId) {
            throw StationException.from(StationErrorCode.FORBIDDEN);
        }
        stationService.deleteStation(station);
    }

}
