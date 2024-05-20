package kr.co.littleriders.backend.global.jwt;


import lombok.Getter;


public class JwtToken {


    @Getter
    private final String accessToken;

    private final long accessTokenExpTime;


    @Getter

    private final String refreshToken;

    private final long refreshTokenExpTime;

    private JwtToken(String accessToken, long accessTokenExpTime, String refreshToken, long refreshTokenExpTime) {
        this.accessToken = accessToken;
        this.accessTokenExpTime = accessTokenExpTime;
        this.refreshToken = refreshToken;
        this.refreshTokenExpTime = refreshTokenExpTime;
    }

    public static JwtToken of(String accessToken, long accessTokenExpTime, String refreshToken, long refreshTokenExpTime) {

        return new JwtToken(accessToken, accessTokenExpTime, refreshToken, refreshTokenExpTime);
    }

    public int getRefreshTokenExpTimeToSecond(){
        return (int) (this.refreshTokenExpTime/1000);
    }

    public int getAccessTokenExpTimeToSecond(){
        return (int) (this.accessTokenExpTime/1000);
    }
}
