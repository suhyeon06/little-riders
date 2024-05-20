package kr.co.littleriders.backend.global.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import kr.co.littleriders.backend.global.entity.MemberType;
import kr.co.littleriders.backend.global.error.code.AuthErrorCode;
import kr.co.littleriders.backend.global.error.exception.AuthException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

@Component
public class JwtProvider {


    private final long ACCESS_TOKEN_EXPIRE_TIME;


    private final long REFRESH_TOKEN_EXPIRE_TIME;

    private final String MEMBER_TYPE = "MEMBER_TYPE";

    private final SecretKey ACCESS_SECRET_KEY;
    private final SecretKey REFRESH_SECRET_KEY;

    public JwtProvider(@Value("${spring.jwt.access.expTime}") long accessTokenExpireTime, @Value("${spring.jwt.refresh.expTime}") long refreshTokenExpireTime, @Value("${spring.jwt.access.secret}") String accessKey, @Value("${spring.jwt.refresh.secret}") String refreshKey) {

        this.ACCESS_TOKEN_EXPIRE_TIME = accessTokenExpireTime;
        this.REFRESH_TOKEN_EXPIRE_TIME = refreshTokenExpireTime;

        this.ACCESS_SECRET_KEY = new SecretKeySpec(accessKey.getBytes(), SignatureAlgorithm.HS512.getJcaName());
        this.REFRESH_SECRET_KEY = new SecretKeySpec(refreshKey.getBytes(), SignatureAlgorithm.HS512.getJcaName());
    }

    public JwtToken createToken(Long id, MemberType memberType) {
        long now = System.currentTimeMillis();
        String userId = id.toString();
        String accessToken = createAccessToken(userId, now, memberType);
        String refreshToken = createRefreshToken(userId, now, memberType);
        return JwtToken.of(accessToken, ACCESS_TOKEN_EXPIRE_TIME, refreshToken, REFRESH_TOKEN_EXPIRE_TIME);
    }

    private String createAccessToken(String userId, long now, MemberType memberType) {
        return Jwts.builder()
                .setSubject(userId)
                .claim(MEMBER_TYPE, memberType.name())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(ACCESS_SECRET_KEY)
                .compact();
    }

    private String createRefreshToken(String userId, long now, MemberType memberType) {
        return Jwts.builder()
                .setSubject(userId)
                .claim(MEMBER_TYPE, memberType.name())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(REFRESH_SECRET_KEY)
                .compact();
    }

    public JwtMemberInfo getJwtMemberInfoByRefreshToken(String token){
        return getJwtMemberInfoByTokenAndSecret(token,REFRESH_SECRET_KEY);
    }

    public JwtMemberInfo getJwtMemberInfoByAccessToken(String token){
        return getJwtMemberInfoByTokenAndSecret(token,ACCESS_SECRET_KEY);
    }

    private JwtMemberInfo getJwtMemberInfoByTokenAndSecret(String token, SecretKey secretKey){
        Claims claims = validationAndParseClaimsByTokenAndSecret(token,secretKey);
        Long id = Long.valueOf(claims.getSubject());
        MemberType memberType = MemberType.valueOf(claims.get(MEMBER_TYPE,String.class));
        return JwtMemberInfo.of(id,memberType);
    }

//
//    public Member getMember(String token, MemberType memberType) {
//        token = token.replace("Bearer ", "");
//        Claims claims = validationAndparesClaimsByAccessToken(token);
//        String id = claims.getSubject();
//        MemberType tokenMemberType = MemberType.valueOf(claims.get(MEMBER_TYPE).toString());
//
//        if (tokenMemberType == MemberType.ACADEMY) {
//
//        }
//
//
//    }
//


    private Claims validationAndParseClaimsByTokenAndSecret(String token,SecretKey secretKey) {
        try {
            return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
        }catch (SignatureException e){
            throw AuthException.from(AuthErrorCode.AUTHORIZATION_NOT_VALID);
        }
        catch (ExpiredJwtException e) {
            throw AuthException.from(AuthErrorCode.JWT_EXPIRED);
        } catch (UnsupportedJwtException e) {
            throw AuthException.from(AuthErrorCode.JWT_NOT_SUPPORT);
        } catch (IllegalArgumentException e) {
            throw AuthException.from(AuthErrorCode.JWT_KEY_NOT_VALID);
        }
    }


}

