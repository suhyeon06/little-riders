package kr.co.littleriders.backend.application.facade.impl;

import kr.co.littleriders.backend.application.client.AddressConvertFetchAPI;
import kr.co.littleriders.backend.application.client.AddressConvertPositionClientRequest;
import kr.co.littleriders.backend.application.client.AddressConvertPositionClientResponse;
import kr.co.littleriders.backend.application.dto.request.AcademySignUpRequest;
import kr.co.littleriders.backend.application.facade.AccountFacade;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.error.code.AcademyErrorCode;
import kr.co.littleriders.backend.domain.academy.error.exception.AcademyException;
import kr.co.littleriders.backend.domain.terminal.TerminalService;
import kr.co.littleriders.backend.domain.terminal.entity.Terminal;
import kr.co.littleriders.backend.domain.terminal.error.code.ShuttleTerminalAttachErrorCode;
import kr.co.littleriders.backend.domain.terminal.error.exception.ShuttleTerminalAttachException;
import kr.co.littleriders.backend.domain.token.RefreshTokenService;
import kr.co.littleriders.backend.domain.token.SignUpTokenService;
import kr.co.littleriders.backend.domain.token.entity.RefreshToken;
import kr.co.littleriders.backend.domain.token.entity.SignUpToken;
import kr.co.littleriders.backend.domain.token.entity.SignUpTokenType;
import kr.co.littleriders.backend.domain.verification.VerificationService;
import kr.co.littleriders.backend.domain.verification.entity.Verification;
import kr.co.littleriders.backend.domain.verification.entity.VerificationType;
import kr.co.littleriders.backend.domain.verification.error.code.VerificationErrorCode;
import kr.co.littleriders.backend.domain.verification.error.exception.VerificationException;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import kr.co.littleriders.backend.global.auth.dto.AuthDTO;
import kr.co.littleriders.backend.global.entity.MemberType;
import kr.co.littleriders.backend.global.error.code.AuthErrorCode;
import kr.co.littleriders.backend.global.error.code.MemberErrorCode;
import kr.co.littleriders.backend.global.error.exception.AuthException;
import kr.co.littleriders.backend.global.error.exception.MemberException;
import kr.co.littleriders.backend.global.jwt.JwtMemberInfo;
import kr.co.littleriders.backend.global.jwt.JwtProvider;
import kr.co.littleriders.backend.global.jwt.JwtToken;
import kr.co.littleriders.backend.global.mail.MailHelper;
import kr.co.littleriders.backend.global.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
class AccountFacadeImpl implements AccountFacade {

    private final RefreshTokenService refreshTokenService;

    private final JwtProvider jwtProvider;


    private final AcademyService academyService;

    private final PasswordUtil passwordUtil;

    private final TerminalService terminalService;

    private final VerificationService verificationService;

    private final MailHelper mailHelper;

    private final SignUpTokenService signUpTokenService;

    private final AddressConvertFetchAPI addressConvertFetchAPI;

    @Override
    @Transactional
    public JwtToken tokenReIssue(final String token) {
        RefreshToken refreshToken = refreshTokenService.findByToken(token);
        refreshTokenService.delete(refreshToken);
        JwtMemberInfo jwtMemberInfo = jwtProvider.getJwtMemberInfoByRefreshToken(token);

        Long id = jwtMemberInfo.getMemberId();
        MemberType memberType = jwtMemberInfo.getMemberType();


        if (memberType == MemberType.ACADEMY) {
            if (academyService.notExistsById(id)) { //신원 검증
                throw AcademyException.from(AcademyErrorCode.NOT_FOUND);
            }
        }
        if (memberType == MemberType.TERMINAL) {
            Terminal terminal = terminalService.findById(id);
            if (terminal.getShuttleTerminalAttach() == null) {
                throw ShuttleTerminalAttachException.from(ShuttleTerminalAttachErrorCode.NOT_FOUND);
            }
        }

        JwtToken jwtToken = jwtProvider.createToken(id, memberType);
        refreshToken = RefreshToken.of(jwtToken.getRefreshToken(), jwtToken.getRefreshTokenExpTimeToSecond());
        refreshTokenService.save(refreshToken);
        return jwtToken;
    }

    @Override
    @Transactional
    public void signOut(String requestRefreshToken) {
        RefreshToken refreshToken = refreshTokenService.findByToken(requestRefreshToken);
        refreshTokenService.delete(refreshToken);
    }

    @Override
    @Transactional
    public JwtToken signIn(String email, String password) { //리팩토링이 될거같음

        Academy academy = academyService.findByEmail(email);
        if (passwordUtil.notEqualsPassword(password, academy.getPassword())) {
            throw AuthException.from(AuthErrorCode.PASSWORD_NOT_EQUAL);
        }
        JwtToken jwtToken = jwtProvider.createToken(academy.getId(), MemberType.ACADEMY);
        RefreshToken refreshToken = RefreshToken.of(jwtToken.getRefreshToken(), jwtToken.getRefreshTokenExpTimeToSecond());
        refreshTokenService.save(refreshToken);
        return jwtToken;


    }

    @Override
    @Transactional
    public void signUp(final AcademySignUpRequest academySignUpRequest, final String token) {

        String email = academySignUpRequest.getEmail();
        SignUpToken signUpToken = signUpTokenService.findAcademySignUpTokenByEmailAndToken(email, token);
        signUpTokenService.delete(signUpToken);
        if (academyService.existsByEmail(email)) {
            throw MemberException.from(MemberErrorCode.ALREADY_EMAIL_EXIST);
        }

        String address = academySignUpRequest.getAddress();
        AddressConvertPositionClientRequest addressConvertPositionClientRequest = AddressConvertPositionClientRequest.from(address);

        AddressConvertPositionClientResponse addressConvertPositionClientResponse = addressConvertFetchAPI.fetchAPI(addressConvertPositionClientRequest);

        double latitude = addressConvertPositionClientResponse.getLatitude();
        double longitude = addressConvertPositionClientResponse.getLongitude();
        Academy academy = academySignUpRequest.toAcademy(passwordUtil,latitude,longitude);
        academyService.save(academy);
    }



    @Override
    @Transactional
    public JwtToken signInByTerminalNumber(String terminalNumber) {
        Terminal terminal = terminalService.findByTerminalNumber(terminalNumber);
        if (terminal.getShuttleTerminalAttach() == null) {
            throw ShuttleTerminalAttachException.from(ShuttleTerminalAttachErrorCode.NOT_FOUND);
        }
        JwtToken jwtToken = jwtProvider.createToken(terminal.getId(), MemberType.TERMINAL);
        RefreshToken refreshToken = RefreshToken.of(jwtToken.getRefreshToken(), jwtToken.getRefreshTokenExpTimeToSecond());
        refreshTokenService.save(refreshToken);
        return jwtToken;
    }

    @Override
    @Transactional
    public void sendChangePasswordEmail(String email) {
        Academy academy = academyService.findByEmail(email);

        VerificationType verificationType = VerificationType.CHANGE_PASSWORD;
        Verification verification = Verification.of(academy.getEmail(), verificationType);
        verificationService.save(verification);
        String code = verification.getCode();
        mailHelper.sendChangePasswordVerificationEmail(email, code);

    }

    @Override
    @Transactional
    public JwtToken signInByEmailAndVerificationCode(String email, String code) {
        Verification verification = verificationService.findByEmail(email);
        VerificationType verificationType = verification.getType();
        if (verificationType != VerificationType.CHANGE_PASSWORD) {
            throw VerificationException.from(VerificationErrorCode.NOT_FOUND);
        }
        long id = academyService.findByEmail(email).getId();
        JwtToken jwtToken = jwtProvider.createToken(id, MemberType.ACADEMY);
        RefreshToken refreshToken = RefreshToken.of(jwtToken.getRefreshToken(), jwtToken.getRefreshTokenExpTimeToSecond());
        verificationService.delete(verification);
        refreshTokenService.save(refreshToken);
        return jwtToken;
    }

    @Override
    @Transactional
    public void changePassword(AuthDTO authDTO, String password) {
        String encrypted = passwordUtil.encrypt(password);
        if (authDTO instanceof AuthAcademy authAcademy) {
            Academy academy = academyService.findById(authAcademy.getId());
            academy.setPassword(encrypted);
            academyService.save(academy);
            return;
        }
        throw AuthException.from(AuthErrorCode.NOT_VALID_REQUEST);
    }

    @Override
    @Transactional
    public String sendSignUpEmail(final String email) {
        if ( academyService.existsByEmail(email)) {
            throw MemberException.from(MemberErrorCode.ALREADY_EMAIL_EXIST);
        }
        Verification verification = Verification.of(email, VerificationType.SIGN_UP);
        verificationService.save(verification);
        String code = verification.getCode();
        mailHelper.sendSignUpVerificationEmail(email, code);
        return code;
    }



    @Override
    @Transactional
    public String getSignUpToken(String email, String code) {
        Verification verification = verificationService.findAcademySignUpByEmailAndCode(email, code);
        verificationService.delete(verification);
        SignUpToken signUpToken = SignUpToken.of(email, SignUpTokenType.ACADEMY);
        signUpTokenService.save(signUpToken);
        return signUpToken.getToken();

    }


}
