package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.StationRequest;
import kr.co.littleriders.backend.application.dto.response.StationResponse;

import java.util.List;

public interface StationFacade {

    void createStation(long academyId, StationRequest stationRequest);

    List<StationResponse> searchByName(String name, long academyId);

    void updateStation(long academyId, long stationId, StationRequest stationRequest);

    void deleteStation(long academyId, long stationId);
}
