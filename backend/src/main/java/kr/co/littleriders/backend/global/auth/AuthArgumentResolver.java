package kr.co.littleriders.backend.global.auth;


import jakarta.servlet.http.HttpServletRequest;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.terminal.TerminalService;
import kr.co.littleriders.backend.domain.terminal.entity.ShuttleTerminalAttach;
import kr.co.littleriders.backend.domain.terminal.entity.Terminal;
import kr.co.littleriders.backend.domain.terminal.error.code.ShuttleTerminalAttachErrorCode;
import kr.co.littleriders.backend.domain.terminal.error.exception.ShuttleTerminalAttachException;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import kr.co.littleriders.backend.global.auth.dto.AuthDTO;
import kr.co.littleriders.backend.global.auth.dto.AuthTerminal;
import kr.co.littleriders.backend.global.entity.MemberType;
import kr.co.littleriders.backend.global.error.code.AuthErrorCode;
import kr.co.littleriders.backend.global.error.exception.AuthException;
import kr.co.littleriders.backend.global.jwt.JwtMemberInfo;
import kr.co.littleriders.backend.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class AuthArgumentResolver implements HandlerMethodArgumentResolver {
    private final JwtProvider jwtProvider;
    private final AcademyService academyService;
    private final TerminalService terminalService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> parameterType = parameter.getParameter().getType();
        return parameter.hasParameterAnnotation(Auth.class) && AuthDTO.class.isAssignableFrom(parameterType);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {


        Class<?> parameterType = parameter.getParameterType();

        //코드상에서 @Auth 가 붙었을떄 valid 한 타입인지 확인
        MemberType parameterMemberType = MemberType.valueOf(parameterType); //parameterType.equals(AuthFamily.class) ? MemberType.FAMILY : MemberType.ACADEMY;


        //jwt 헤더에서 파싱 부분
        HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        if (httpServletRequest == null) {
            throw AuthException.from(AuthErrorCode.JWT_NOT_FOUND);
        }

        String token = httpServletRequest.getHeader("Authorization");
        if (token == null) {
            throw AuthException.from(AuthErrorCode.JWT_NOT_FOUND);
        }
        if (!token.startsWith("Bearer ")) {
            throw AuthException.from(AuthErrorCode.AUTHORIZATION_NOT_VALID);
        }

        //멤버 조회
        token = token.substring(7);
        JwtMemberInfo jwtMemberInfo = jwtProvider.getJwtMemberInfoByAccessToken(token);
        long memberId = jwtMemberInfo.getMemberId();
        MemberType memberType = jwtMemberInfo.getMemberType();

        //유효하지 않은 멤버 타입인 경우
        if (parameterMemberType != memberType && !parameterType.equals(AuthDTO.class)) {
            throw AuthException.from(AuthErrorCode.JWT_NOT_SUPPORT);
        }


        if (memberType == MemberType.ACADEMY) {
            Academy academy = academyService.findById(memberId);
            return AuthAcademy.from(academy);
        }
        if(memberType == MemberType.TERMINAL){
            Terminal terminal = terminalService.findById(memberId);
            ShuttleTerminalAttach shuttleTerminalAttach = terminal.getShuttleTerminalAttach();

            //부착 정보가 없으면 익셉션 발생
            if(shuttleTerminalAttach == null){
                throw ShuttleTerminalAttachException.from(ShuttleTerminalAttachErrorCode.NOT_FOUND);
            }
            Shuttle shuttle = shuttleTerminalAttach.getShuttle();

            return AuthTerminal.of(terminal,shuttle);

        }
        throw AuthException.from(AuthErrorCode.UNKNOWN_ERROR);

    }
}
