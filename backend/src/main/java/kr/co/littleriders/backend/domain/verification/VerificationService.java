package kr.co.littleriders.backend.domain.verification;

import kr.co.littleriders.backend.domain.verification.entity.Verification;

public interface VerificationService {

    Verification findByEmail(String email);

    void save(Verification verification);

    void extendsTimeByEmail(String email);

    void delete(Verification verification);

    Verification findAcademySignUpByEmailAndCode(String email, String code);
}
