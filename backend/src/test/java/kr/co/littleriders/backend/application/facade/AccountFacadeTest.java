package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.AcademySignUpRequest;
import kr.co.littleriders.backend.application.dto.request.SignInRequest;
import kr.co.littleriders.backend.common.fixture.AcademyFixture;
import kr.co.littleriders.backend.global.jwt.JwtToken;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class AccountFacadeTest {

    @Autowired
    AccountFacade accountFacade;



    @Nested
    @DisplayName("tokenReIssue 테스트")
    class tokenReIssue{

        @Test
        @DisplayName("성공")
        void whenSuccess(){



            AcademyFixture academyFixture  = AcademyFixture.BOXING;
            String email = academyFixture.getEmail();
            String password = academyFixture.getPassword();
            String mailReceived = accountFacade.sendSignUpEmail(email);

            String uuid = accountFacade.getSignUpToken(email, mailReceived);
            AcademySignUpRequest academySignUpRequest = academyFixture.toAcademySignUpRequest();
            accountFacade.signUp(academySignUpRequest,uuid);

            SignInRequest signInRequest = new SignInRequest(email,password);
            JwtToken jwtToken = accountFacade.signIn(signInRequest.getEmail(), signInRequest.getPassword());
            String token = jwtToken.getRefreshToken();
            JwtToken reIssuedToken = accountFacade.tokenReIssue(token);
            log.info("accessToken=[{}]",reIssuedToken.getAccessToken());
            log.info("refreshToken=[{}]",reIssuedToken.getRefreshToken());
        }
    }
}