package kr.co.littleriders.backend.application.facade;

import java.util.List;

import kr.co.littleriders.backend.application.dto.request.ChildRegistRequest;
import kr.co.littleriders.backend.application.dto.response.ChildDetailResponse;
import kr.co.littleriders.backend.application.dto.response.ChildListResponse;
import org.springframework.core.io.Resource;

public interface FamilyChildFacade {

	Long insertChild(ChildRegistRequest childRegistRequest, Long familyId);

	List<ChildListResponse> readChildList(Long familyId);

	ChildDetailResponse readChildDetail(Long familyId, Long childId);

    Resource readChildImage(Long familyId, Long childId);
}
