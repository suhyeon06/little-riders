package kr.co.littleriders.backend.global.error;


import kr.co.littleriders.backend.global.error.code.LittleRidersErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import kr.co.littleriders.backend.global.response.api.LittleRidersErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<LittleRidersErrorResponse> handleBaseException(LittleRidersException e) {
//        e.printStackTrace();
        final LittleRidersErrorCode littleRidersErrorCode = e.getErrorCode();
        return ResponseEntity.status(littleRidersErrorCode.getStatus()).body(LittleRidersErrorResponse.of(littleRidersErrorCode.getCode(),littleRidersErrorCode.getMessage()));
    }

    @ExceptionHandler
    protected ResponseEntity<?> handleMethodArgumentNotValidException( //todo : 익셉션 변경 필요
            MethodArgumentNotValidException e ) {
//        final ErrorResponse response = ErrorResponse.of(API_ERROR_INPUT_INVALID_VALUE, e.getBindingResult());
//        log.warn(e.getMessage());
        Map<String,String> response = new HashMap<>();
        response.put("code","ERROR_1");
        response.put("error",e.getMessage());

        return ResponseEntity.status(500).body(response);
    }


    @ExceptionHandler
    protected ResponseEntity<?> handleWebClientException(WebClientResponseException e){
        Throwable rootCause = e.getRootCause();
        if(rootCause instanceof LittleRidersException){
            throw (LittleRidersException)rootCause;
        }

        //TODO 수정 필요
        Map<String,String> response = new HashMap<>();
        response.put("code","ERROR_1");
        response.put("error",e.getMessage());
        return ResponseEntity.status(500).body(response);



    }

}
