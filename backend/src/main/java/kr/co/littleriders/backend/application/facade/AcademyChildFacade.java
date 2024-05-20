package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.CreateAcademyChildRequest;
import kr.co.littleriders.backend.application.dto.request.UpdateAcademyChildRequest;
import kr.co.littleriders.backend.application.dto.response.AcademyChildDetailResponse;
import kr.co.littleriders.backend.application.dto.response.AcademyChildResponse;
import kr.co.littleriders.backend.application.dto.response.BeaconResponse;

import java.util.List;

public interface AcademyChildFacade {

    List<AcademyChildResponse> getAcademyChildListByAcademyId(Long academyId);

    Long updateAcademyChildStatus(Long academyId, Long academyChildId, String status);

    Long updateAcademyChild(Long academyId, Long academyChildId, UpdateAcademyChildRequest updateAcademyChildRequest);

    AcademyChildDetailResponse getAcademyChildDetail(Long academyId, Long academyChildId);

    Long insertAcademyChild(Long academyId, CreateAcademyChildRequest createAcademyChildRequest);

	List<BeaconResponse> getBeaconList(Long academyId);
}
