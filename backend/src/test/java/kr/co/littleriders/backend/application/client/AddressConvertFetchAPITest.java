package kr.co.littleriders.backend.application.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Slf4j
class AddressConvertFetchAPITest {

    @Autowired
    AddressConvertFetchAPI addressConvertFetchAPI;


    @Test
    @DisplayName("성공")
    void whenSuccess() throws JsonProcessingException {

        AddressConvertPositionClientRequest request = AddressConvertPositionClientRequest.from("테헤란로 212");
        AddressConvertPositionClientResponse response = addressConvertFetchAPI.fetchAPI(request);
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("response = [{}]", objectMapper.writeValueAsString(response));

    }

    @Test
    @DisplayName("실패, 정보가 없는 경우")
    void whenFailNotExistsData() {

        AddressConvertPositionClientRequest request = AddressConvertPositionClientRequest.from("테헤란로");

        assertThrows(WebClientResponseException.class, ()->{
            addressConvertFetchAPI.fetchAPI(request);
        });


    }

}