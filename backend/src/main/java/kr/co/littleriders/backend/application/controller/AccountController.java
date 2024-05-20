package kr.co.littleriders.backend.application.controller;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import kr.co.littleriders.backend.application.dto.request.AcademySignUpRequest;
import kr.co.littleriders.backend.application.dto.request.ChangePasswordRequest;
import kr.co.littleriders.backend.application.dto.request.SignInRequest;
import kr.co.littleriders.backend.application.dto.request.ValidateEmailRequest;
import kr.co.littleriders.backend.application.facade.AccountFacade;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthDTO;
import kr.co.littleriders.backend.global.jwt.JwtToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController {

    private final AccountFacade accountFacade;

    @GetMapping("/sign-up/validate")
    public ResponseEntity<Void> sendSignUpVerificationMail(@RequestParam(name = "email") @NotBlank String email) { //TODO : 변경필요
        accountFacade.sendSignUpEmail(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-up/validate")
    public ResponseEntity<?> validateEmailWithCode(@Valid @RequestBody ValidateEmailRequest validateEmailRequest, HttpServletResponse response) {
        log.info("validateEmailWithCode: call");
        String email = validateEmailRequest.getEmail();
        String code = validateEmailRequest.getCode();
        String signUpToken = accountFacade.getSignUpToken(email, code);
        Cookie cookie = new Cookie("signup-token", signUpToken);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60*30);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@Valid @RequestBody AcademySignUpRequest academySignUpRequest, @CookieValue("signup-token") String token) {
        log.info("signup-token = [{}]",token);
        accountFacade.signUp(academySignUpRequest,token);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/re-issue")
    public ResponseEntity<Void> reIssueTokenByRefresh(@CookieValue("refresh-token") String requestRefreshToken, HttpServletResponse response) {
        JwtToken jwtToken = accountFacade.tokenReIssue(requestRefreshToken);
        String accessToken = jwtToken.getAccessToken();
        String refreshToken = jwtToken.getRefreshToken();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        Cookie cookie = new Cookie("refresh-token", refreshToken);
        cookie.setMaxAge(jwtToken.getRefreshTokenExpTimeToSecond());
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok().headers(headers).build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Void> signIn(@Valid @RequestBody SignInRequest signInRequest, HttpServletResponse response) {
        String email = signInRequest.getEmail();
        String password = signInRequest.getPassword();
        JwtToken jwtToken = accountFacade.signIn(email, password);
        String accessToken = jwtToken.getAccessToken();
        String refreshToken = jwtToken.getRefreshToken();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        Cookie cookie = new Cookie("refresh-token", refreshToken);
        cookie.setMaxAge(jwtToken.getRefreshTokenExpTimeToSecond());
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok().headers(headers).build();
    }

    @GetMapping("/sign-out")
    public ResponseEntity<Void> signOut(@CookieValue("refresh-token") String requestRefreshToken, HttpServletResponse response) {
        accountFacade.signOut(requestRefreshToken);
        Cookie cookie = new Cookie("refresh-token", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/change-password")
    public ResponseEntity<Void> sendChangePasswordEmail(@RequestParam String email) {
        accountFacade.sendChangePasswordEmail(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/change-password")
    public ResponseEntity<Void> signInByEmailAndCode(@RequestBody ValidateEmailRequest validateEmailRequest, HttpServletResponse response) {
        String email = validateEmailRequest.getEmail();
        String code = validateEmailRequest.getCode();
        JwtToken jwtToken = accountFacade.signInByEmailAndVerificationCode(email, code);
        String accessToken = jwtToken.getAccessToken();
        String refreshToken = jwtToken.getRefreshToken();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        Cookie cookie = new Cookie("refresh-token", refreshToken);
        cookie.setMaxAge(jwtToken.getRefreshTokenExpTimeToSecond());
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok().headers(headers).build();
    }

    @PutMapping("/change-password")
    public ResponseEntity<Void> changePassword(@Auth AuthDTO authDTO, @RequestBody ChangePasswordRequest changePasswordRequest){
        accountFacade.changePassword(authDTO,changePasswordRequest.getPassword());
        return ResponseEntity.ok().build();
    }

}
