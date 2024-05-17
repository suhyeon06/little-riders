package kr.co.littleriders.backend.application.facade.impl;

import kr.co.littleriders.backend.application.dto.response.AcademyLocationResponse;
import kr.co.littleriders.backend.application.facade.AcademyFacade;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AcademyFacadeImpl implements AcademyFacade {

    private final AcademyService academyService;

    @Override
    public AcademyLocationResponse readAcademyAddress(Long academyId) {

        Academy academy = academyService.findById(academyId);

        return AcademyLocationResponse.from(academy);
    }
}
