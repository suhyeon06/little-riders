package kr.co.littleriders.backend.global.response.api;

import lombok.Getter;

@Getter
public class LittleRidersErrorResponse {
    private String code;

    private String message;
    private LittleRidersErrorResponse(String code,String message) {
        this.code = code;
        this.message = message;
    }

    public static LittleRidersErrorResponse of(String code,String message){
        return new LittleRidersErrorResponse(code,message);
    }
}
