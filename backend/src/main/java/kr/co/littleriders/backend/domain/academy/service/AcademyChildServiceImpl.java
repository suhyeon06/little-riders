package kr.co.littleriders.backend.domain.academy.service;

import kr.co.littleriders.backend.domain.academy.AcademyChildService;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.academy.error.code.AcademyChildErrorCode;
import kr.co.littleriders.backend.domain.academy.error.exception.AcademyChildException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class AcademyChildServiceImpl implements AcademyChildService {

    private final AcademyChildRepository academyChildRepository;

    @Override
    public AcademyChild findById(long id) {
        return academyChildRepository.findById(id).orElseThrow(
                () -> AcademyChildException.from(AcademyChildErrorCode.NOT_FOUND)
        );
    }

    @Override
    public boolean existsById(long id) {
        return academyChildRepository.existsById(id);
    }

    @Override
    public boolean notExistsById(long id) {
        return !academyChildRepository.existsById(id);
    }

    @Override
    @Transactional
    public long save(AcademyChild academyChild) {
        return academyChildRepository.save(academyChild).getId();
    }

}
