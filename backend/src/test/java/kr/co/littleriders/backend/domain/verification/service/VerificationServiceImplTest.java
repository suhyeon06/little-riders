//package kr.co.littleriders.backend.domain.verification.service;
//
//import kr.co.littleriders.backend.domain.verification.entity.Verification;
//import kr.co.littleriders.backend.domain.verification.entity.VerificationType;
//import kr.co.littleriders.backend.domain.verification.error.code.VerificationErrorCode;
//import kr.co.littleriders.backend.domain.verification.error.exception.VerificationException;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//
//
//@SpringBootTest
//class VerificationServiceImplTest {
//
//    @Autowired
//    VerificationServiceImpl verificationService;
//
//
//    @MockBean
//    VerificationRepository verificationRepository;
//
//    @Nested
//    @DisplayName("findFamilySignUpByEmailAndCode 테스트")
//    class findByEmailAndCodeAndType {
//
//
//        @Test
//        @DisplayName("성공")
//        void whenSuccess() {
//            //given
//            Verification verification = Verification.of("test@exampl.com", VerificationType.FAMILY_SIGN_UP);
//            //verificationRepository.save(verification); //실제로 데이터를 넣어보는거고
//
//            given(verificationRepository.findByEmail(any())).willReturn(Optional.of(verification));
//
//            Verification v = verificationService.findFamilySignUpByEmailAndCode(verification.getEmail(), verification.getCode());
//            assertEquals(verification, v);
//        }
//
//
//        @Test
//        @DisplayName("실패, 이메일이 없는 경우")
//        void whenFailEmailNotExists() {
//
//
//            Verification verification = Verification.of("test@naver.com", VerificationType.FAMILY_SIGN_UP);
//
//            //given
//            given(verificationRepository.findByEmail(any())).willReturn(Optional.empty());
//
//
//            //when
//            VerificationException verificationException = assertThrows(VerificationException.class, () -> {
//                verificationService.findFamilySignUpByEmailAndCode(verification.getEmail(), verification.getCode());
//
//            });
//            //then
//            assertEquals(verificationException.getErrorCode(), VerificationErrorCode.NOT_FOUND);
//
//        }
//
//
//        @Test
//        @DisplayName("실패, 코드가 일치하지 않는 경우")
//        void whenFailCodeNotEqual() {
//
//
//            Verification verification = Verification.of("test@naver.com", VerificationType.FAMILY_SIGN_UP);
//
//            //given
//            given(verificationRepository.findByEmail(any())).willReturn(Optional.of(verification));
//
//
//            //when
//            VerificationException verificationException = assertThrows(VerificationException.class, () -> {
//                verificationService.findFamilySignUpByEmailAndCode(verification.getEmail(), verification.getCode() + "1");
//
//            });
//            //then
//            assertEquals(verificationException.getErrorCode(), VerificationErrorCode.NOT_FOUND);
//
//        }
//
//
//        @Test
//        @DisplayName("실패, 타입이 일치하지 않는 경우")
//        void whenFailTypeNotEqual() {
//
//
//            Verification verification = Verification.of("test@naver.com", VerificationType.FAMILY_CHANGE_PASSWORD);
//
//            //given
//            given(verificationRepository.findByEmail(any())).willReturn(Optional.of(verification));
//
//
//            //when
//            VerificationException verificationException = assertThrows(VerificationException.class, () -> {
//                verificationService.findFamilySignUpByEmailAndCode(verification.getEmail(), verification.getCode());
//
//            });
//            //then
//            assertEquals(verificationException.getErrorCode(), VerificationErrorCode.NOT_FOUND);
//
//        }
//    }
//}