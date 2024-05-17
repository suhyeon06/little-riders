package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.response.AcademyLocationResponse;

public interface AcademyFacade {

    AcademyLocationResponse readAcademyAddress(Long academyId);
}
