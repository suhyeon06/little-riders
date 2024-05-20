package kr.co.littleriders.backend.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfig {

    private final String NAVER_KEY;
    private final String NAVER_SECRET;

    public ClientConfig(
            @Value("${spring.naver.key}")
            String naverKey,
            @Value("${spring.naver.secret}")
            String naverSecret) {
        NAVER_KEY = naverKey;
        NAVER_SECRET = naverSecret;
    }


    @Bean
    public WebClient addressConvertClient() {
        return WebClient.builder()
                .baseUrl("https://naveropenapi.apigw.ntruss.com")
                .defaultHeader("X-NCP-APIGW-API-KEY",NAVER_SECRET)
                .defaultHeader("X-NCP-APIGW-API-KEY-ID",NAVER_KEY)
                .build();
    }
}
