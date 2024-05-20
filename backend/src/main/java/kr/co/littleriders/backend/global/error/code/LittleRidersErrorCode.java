package kr.co.littleriders.backend.global.error.code;

import org.springframework.http.HttpStatus;

public interface LittleRidersErrorCode {
    HttpStatus getStatus();

    String getCode();

    String getMessage();
}
