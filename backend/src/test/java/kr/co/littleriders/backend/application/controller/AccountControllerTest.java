//package kr.co.littleriders.backend.application.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.http.Cookie;
//import kr.co.littleriders.backend.application.dto.request.ChangePasswordRequest;
//import kr.co.littleriders.backend.application.dto.request.FamilySignUpRequest;
//import kr.co.littleriders.backend.application.dto.request.SignInRequest;
//import kr.co.littleriders.backend.domain.academy.AcademyService;
//import kr.co.littleriders.backend.domain.academy.entity.Academy;
//import kr.co.littleriders.backend.domain.family.FamilyService;
//import kr.co.littleriders.backend.domain.family.entity.Family;
//import kr.co.littleriders.backend.global.entity.MemberType;
//import kr.co.littleriders.backend.global.jwt.JwtProvider;
//import kr.co.littleriders.backend.global.jwt.JwtToken;
//import kr.co.littleriders.backend.global.utils.PasswordUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@SpringBootTest
//@Slf4j
//@AutoConfigureMockMvc
//@Transactional
//class AccountControllerTest {
//
//
//    @Autowired
//    AcademyService academyService;
//
//    @Autowired
//    JwtProvider jwtProvider;
//
//
//    @Autowired
//    PasswordUtil passwordUtil;
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//
//    @Nested
//    @DisplayName("reIssue 테스트")
//    class reIssue {
//
//        @Test
//        @DisplayName("성공")
//        void whenSuccess() throws Exception {
//            //회원가입 후
//            //로그인 한 다음
//            String email = "test@example.com";
//            String password = "1234";
//            String name = "54";
//            String phoneNumber = "123456";
//            Family family = new FamilySignUpRequest(
//                    email,
//                    password,
//                    name,
//                    "집주소",
//                    phoneNumber
//            ).toFamily(
//                    passwordUtil
//            );
//            log.info("family = [{}]",objectMapper.writeValueAsString(family));
//            familyService.save(family);
//            SignInRequest signInRequest = new SignInRequest(email, password);
//            MvcResult signInResult = mockMvc.perform(
//                            post("/family/account/sign-in")
//                                    .contentType(MediaType.APPLICATION_JSON)
//                                    .content(objectMapper.writeValueAsString(signInRequest))
//
//                    )
//                    .andExpect(status().isOk())
//                    .andExpect(header().exists("Authorization"))
//                    .andDo(print())
//                    .andReturn();
//            Cookie cookie = signInResult.getResponse().getCookie("refresh-token");
//            String accessToken = signInResult.getResponse().getHeader("Authorization");
//            log.info("accessToken=[{}]", accessToken);
//            MvcResult reIssueResult = mockMvc.perform(
//                            get("/account/re-issue")
//                                    .contentType(MediaType.APPLICATION_JSON)
//                                    .cookie(cookie)
//                    )
//                    .andExpect(status().isOk())
//                    .andDo(print())
//                    .andReturn();
//
//            String signInRefresh = cookie.getValue();
//            String reIssueRefresh = reIssueResult.getResponse().getCookie("refresh-token").getValue();
//            log.info("signInRefresh = {}",signInRefresh);
//            log.info("reIssueRefresh = {}",reIssueRefresh);
//            assertNotEquals(signInRefresh, reIssueRefresh);
//        }
//    }
//
//
//    @Nested
//    @DisplayName("ArgumentResolver e2e 테스트")
//    class argumentResolver{
//
//        @Test
//        @DisplayName("성공")
//        void whenSuccess() throws Exception {
//
//            //회원가입 후
//            //로그인 한 다음
//            String email = "test@example.com";
//            String password = "1234";
//            String name = "54";
//            String phoneNumber = "123456";
//            Family family = new FamilySignUpRequest(
//                    email,
//                    password,
//                    name,
//                    "집주소",
//                    phoneNumber
//            ).toFamily(
//                    passwordUtil
//            );
//            log.info("family = [{}]",objectMapper.writeValueAsString(family));
//            familyService.save(family);
//            SignInRequest signInRequest = new SignInRequest(email, password);
//            MvcResult signInResult = mockMvc.perform(
//                            post("/family/account/sign-in")
//                                    .contentType(MediaType.APPLICATION_JSON)
//                                    .content(objectMapper.writeValueAsString(signInRequest))
//
//                    )
//                    .andExpect(status().isOk())
//                    .andExpect(header().exists("Authorization"))
//                    .andDo(print())
//                    .andReturn();
//            Cookie cookie = signInResult.getResponse().getCookie("refresh-token");
//            String accessToken = signInResult.getResponse().getHeader("Authorization");
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Authorization", accessToken);
//            log.info("accessToken=[{}]", accessToken);
//            mockMvc.perform(
//                            post("/family/account/change-password")
//                                    .contentType(MediaType.APPLICATION_JSON)
//                                    .headers(headers)
//                                    .cookie(cookie)
//                    )
//                    .andExpect(status().isOk())
//                    .andDo(print())
//                    .andReturn();
//        }
//
//
//
//        @Test
//        @DisplayName("실패, 다른 타입으로 보낼떄")
//        void whenFailNotSupports() throws Exception {
//
//            //회원가입 후
//            //로그인 한 다음
//            String email = "test@example.com";
//            String password = "1234";
//            String name = "54";
//            String phoneNumber = "123456";
//            Family family = new FamilySignUpRequest(
//                    email,
//                    password,
//                    name,
//                    "집주소",
//                    phoneNumber
//            ).toFamily(
//                    passwordUtil
//            );
//            log.info("family = [{}]",objectMapper.writeValueAsString(family));
//            familyService.save(family);
//            SignInRequest signInRequest = new SignInRequest(email, password);
//            MvcResult signInResult = mockMvc.perform(
//                            post("/family/account/sign-in")
//                                    .contentType(MediaType.APPLICATION_JSON)
//                                    .content(objectMapper.writeValueAsString(signInRequest))
//
//                    )
//                    .andExpect(status().isOk())
//                    .andExpect(header().exists("Authorization"))
//                    .andDo(print())
//                    .andReturn();
//            Cookie cookie = signInResult.getResponse().getCookie("refresh-token");
//            String accessToken = signInResult.getResponse().getHeader("Authorization");
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Authorization", accessToken);
//            log.info("accessToken=[{}]", accessToken);
//            mockMvc.perform(
//                            post("/academy/account/change-password")
//                                    .contentType(MediaType.APPLICATION_JSON)
//                                    .headers(headers)
//                                    .cookie(cookie)
//                    )
//                    .andExpect(status().isBadRequest())
//                    .andDo(print())
//                    .andReturn();
//        }
//
//    }
//
//
//    @Nested
//    @DisplayName("로그아웃 테스트")
//    class signOut {
//
//        @Test
//        @DisplayName("성공")
//        void whenSuccess() throws Exception {
//            //회원가입 후
//            //로그인 한 다음
//            String email = "test@example.com";
//            String password = "1234";
//            String name = "54";
//            String phoneNumber = "123456";
//            Family family = new FamilySignUpRequest(
//                    email,
//                    password,
//                    name,
//                    "집주소",
//                    phoneNumber
//            ).toFamily(
//                    passwordUtil
//            );
//            log.info("family = [{}]",objectMapper.writeValueAsString(family));
//            familyService.save(family);
//            SignInRequest signInRequest = new SignInRequest(email, password);
//            MvcResult signInResult = mockMvc.perform(
//                            post("/family/account/sign-in")
//                                    .contentType(MediaType.APPLICATION_JSON)
//                                    .content(objectMapper.writeValueAsString(signInRequest))
//
//                    )
//                    .andExpect(status().isOk())
//                    .andExpect(header().exists("Authorization"))
//                    .andDo(print())
//                    .andReturn();
//            Cookie cookie = signInResult.getResponse().getCookie("refresh-token");
//            String accessToken = signInResult.getResponse().getHeader("Authorization");
//            log.info("accessToken=[{}]", accessToken);
//
//
//
//
//            String signInRefresh = cookie.getValue();
//
//            mockMvc.perform(
//                    get("/account/sign-out")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .cookie(cookie)
//
//            )
//                    .andExpect(status().isOk())
//                    .andDo(print());
//
//
//            MvcResult reIssueResult = mockMvc.perform(
//                            get("/account/re-issue")
//                                    .contentType(MediaType.APPLICATION_JSON)
//                                    .cookie(cookie)
//                    )
//                    .andExpect(status().isNotFound())
//                    .andDo(print())
//                    .andReturn();
//        }
//    }
//
//    @Nested
//    @DisplayName("비밀번호 변경 테스트")
//    class change_password{
//        @Test
//        @DisplayName("Academy 변경 성공")
//        void whenSuccessAcademy() throws Exception {
//
//
////            String beforePassword = "mypassword";
//            Academy academy = Academy.of(
//                    "email",
//                    "beforePassword",
//                    "name",
//                    "address",
//                    "010",
//                    33,
//                    44
//            );
//
//            String beforePassword = academy.getPassword();
//
//            academyService.save(academy);
//            JwtToken jwtToken = jwtProvider.createToken(academy.getId(), MemberType.ACADEMY);
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Authorization", jwtToken.getAccessToken());
//            Map<String,String> request = new HashMap<>();
//            String changeRequestPassword = "1234";
//            ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest(changeRequestPassword);
//
//            request.put("password",changeRequestPassword);
//            mockMvc.perform(
//                    put("/account/change-password")
//                            .header("Authorization","Bearer " +jwtToken.getAccessToken())
//                            .content(objectMapper.writeValueAsString(changePasswordRequest))
//                            .contentType(MediaType.APPLICATION_JSON)
//            ).andDo(print());
//
//
//            String encryptedPassword = academy.getPassword();
//
//            assertNotEquals(changeRequestPassword,encryptedPassword);
//            assertTrue(passwordUtil.equalsPassword(changeRequestPassword,encryptedPassword));
//
//        }
//        @Test
//        @DisplayName("Family 변경 성공")
//        void whenSuccessFamily() throws Exception {
//
//
////            String beforePassword = "mypassword";
//            Family family = Family.of(
//                    "email",
//                    "beforePassword",
//                    "name",
//                    "address",
//                    "010"
//            );
//
//            String beforePassword = family.getPassword();
//
//            familyService.save(family);
//            JwtToken jwtToken = jwtProvider.createToken(family.getId(), MemberType.FAMILY);
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Authorization", jwtToken.getAccessToken());
//            Map<String,String> request = new HashMap<>();
//            String changeRequestPassword = "1234";
//            ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest(changeRequestPassword);
//
//            request.put("password",changeRequestPassword);
//            mockMvc.perform(
//                    put("/account/change-password")
//                            .header("Authorization","Bearer " +jwtToken.getAccessToken())
//                            .content(objectMapper.writeValueAsString(changePasswordRequest))
//                            .contentType(MediaType.APPLICATION_JSON)
//            ).andDo(print());
//
//
//            String encryptedPassword = family.getPassword();
//
//            assertNotEquals(changeRequestPassword,encryptedPassword);
//            assertTrue(passwordUtil.equalsPassword(changeRequestPassword,encryptedPassword));
//
//        }
//
//
//
//    }
//
//
//}