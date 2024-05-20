package kr.co.littleriders.backend.domain.verification.service;


import kr.co.littleriders.backend.domain.verification.VerificationService;
import kr.co.littleriders.backend.domain.verification.entity.Verification;
import kr.co.littleriders.backend.domain.verification.entity.VerificationType;
import kr.co.littleriders.backend.domain.verification.error.code.VerificationErrorCode;
import kr.co.littleriders.backend.domain.verification.error.exception.VerificationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class VerificationServiceImpl implements VerificationService {

    private final VerificationRepository verificationRepository;

    private Verification findByEmailAndCodeAndType(String email, String code, VerificationType verificationType) {

        Verification verification = verificationRepository.findByEmail(email).orElseThrow(
                () -> VerificationException.from(VerificationErrorCode.NOT_FOUND)
        );
        if (verification.notEqualsCode(code) || verification.notEqualsType(verificationType)) {
            throw VerificationException.from(VerificationErrorCode.NOT_FOUND);
        }
        return verification;
    }

    @Override
    public Verification findByEmail(String email) {
        return verificationRepository.findByEmail(email).orElseThrow(
                () -> VerificationException.from(VerificationErrorCode.NOT_FOUND)
        );
    }

    @Override
    @Transactional
    public void save(Verification verification) {
        verificationRepository.save(verification);
    }

    @Override
    @Transactional
    public void extendsTimeByEmail(String email) {
        Verification verification = verificationRepository.findByEmail(email).orElseThrow(
                () -> VerificationException.from(VerificationErrorCode.NOT_FOUND)
        );
        String code = verification.getCode();
        VerificationType verificationType = verification.getType();
        Verification v = Verification.of(email, code, verificationType);
        verificationRepository.save(v);
    }

    @Override
    @Transactional
    public void delete(Verification verification) {
        verificationRepository.delete(verification);
    }

    @Override
    public Verification findAcademySignUpByEmailAndCode(String email, String code) {
        return findByEmailAndCodeAndType(email,code,VerificationType.SIGN_UP);
    }
}
