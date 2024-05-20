package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.DriverRegistRequest;
import kr.co.littleriders.backend.application.dto.response.AcademyDriverResponse;
import kr.co.littleriders.backend.application.dto.response.DriverDetailResponse;

import java.util.List;

public interface AcademyDriverFacade {
	Long insertDriver(DriverRegistRequest driverRegistRequest, Long academyId);

	List<AcademyDriverResponse> readDriverList(Long academyId);

	DriverDetailResponse readDriverDetail(Long academyId, Long driverId);
}
