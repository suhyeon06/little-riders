package kr.co.littleriders.backend.domain.academy;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;


public interface AcademyService {
    Academy findById(long id);

    Academy findByEmail(String email);

    boolean existsById(long id);

    boolean notExistsById(long id);

    boolean existsByEmail(String email);

    boolean notExistsByEmail(String email);

    long save(Academy academy);
}
