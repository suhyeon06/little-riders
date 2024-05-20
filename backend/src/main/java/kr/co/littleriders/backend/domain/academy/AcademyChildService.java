package kr.co.littleriders.backend.domain.academy;

import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;

public interface AcademyChildService {

    AcademyChild findById(long id);

    boolean existsById(long id);

    boolean notExistsById(long id);

    long save(AcademyChild academyChild);

}
