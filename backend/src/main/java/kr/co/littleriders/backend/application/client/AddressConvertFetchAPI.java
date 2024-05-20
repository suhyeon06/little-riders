package kr.co.littleriders.backend.application.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Component
public class AddressConvertFetchAPI {

    private final WebClient addressConvertClient;
    private final String PATH = "/map-geocode/v2/geocode";


    public AddressConvertPositionClientResponse fetchAPI(AddressConvertPositionClientRequest request) {



        String address = request.getAddress();
        return addressConvertClient
                .method(HttpMethod.GET)
                .uri(builder -> builder.path(PATH).queryParam("query", address).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(AddressConvertPositionClientResponse.class)
                .block();
    }

}

