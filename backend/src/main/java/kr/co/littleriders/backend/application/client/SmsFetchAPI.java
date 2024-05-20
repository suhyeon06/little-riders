package kr.co.littleriders.backend.application.client;

import lombok.Getter;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HexFormat;
import java.util.List;

@Component
public class SmsFetchAPI {

    private final WebClient webClient;
    private final String SMS_KEY;
    private final String SMS_SECRET;

    private final String FROM;

    private final boolean onTest;

    public SmsFetchAPI(@Value("${spring.sms.key}") String SMS_KEY, @Value("${spring.sms.secret}") String SMS_SECRET, @Value("${spring.sms.sender}") String from, @Value("${spring.sms.test}") boolean onTest) {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.solapi.com/messages/v4")
                .build();
        this.SMS_KEY = SMS_KEY;
        this.SMS_SECRET = SMS_SECRET;
        this.FROM = from;
        this.onTest = onTest;
    }


    public String sendLMS(SmsSendClientRequest smsSendClientRequest) {
        MessageDTO messageDTO = MessageDTO.of(smsSendClientRequest,FROM);
        return lmsFetch(messageDTO);
    }

    public String sendLMS(List<SmsSendClientRequest> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        MessageDTO messageDTO = MessageDTO.of(list,FROM);
        return lmsFetch(messageDTO);

    }

    private String lmsFetch(MessageDTO messageDTO) {
        if(messageDTO.getMessages().isEmpty()){
            return "";
        }
        final String authorization = generateAuthorization();
        final String BASE_URL = "/send-many/detail";

        if(onTest){
            return "test";
        }

        return webClient
                .method(HttpMethod.POST)
                .uri(BASE_URL)
                .bodyValue(messageDTO)
                .header("Authorization", authorization)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String fetAPI() {

        final String BASE_URL = "/list";

        final String authorization = generateAuthorization();

        return webClient
                .method(HttpMethod.GET)
                .uri(BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", authorization)
                .retrieve()
                .bodyToMono(String.class)
                .doOnError(WebClientResponseException.class, e -> {
                    if (e.getStatusCode().is4xxClientError()) {
                        System.out.println("400 에러 발생");
                        System.out.println("응답 본문: " + e.getResponseBodyAsString());
                    }
                })
                .block();
    }

    private String generateSalt() {
        return BCrypt.gensalt();
    }

    private String generateDate() {
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        return now.format(formatter);
    }

    private String Hmac(String key, String message) {

        try {
            Mac hasher = Mac.getInstance("HmacSHA256");
            hasher.init(new SecretKeySpec(message.getBytes("utf-8"), "HmacSHA256"));
            byte[] hash = hasher.doFinal(key.getBytes());
            return HexFormat.of().formatHex(hash);
        } catch (Exception e) {
            return null;
        }
    }

    private String generateAuthorization() {
        String date = generateDate();
        String salt = generateSalt();
        String key = date + salt;
        String signature = Hmac(key, SMS_SECRET);
        return "HMAC-SHA256 apiKey=" + SMS_KEY + ", date=" + date + ", salt=" + salt + ", signature=" + signature;
    }


    @Getter
    private static class MessageDTO {

        private final List<SmsSendClientRequest> messages;

        private MessageDTO(List<SmsSendClientRequest> messages) {
            this.messages = messages;
        }

        private MessageDTO(SmsSendClientRequest smsSendClientRequest) {
            this.messages = new ArrayList<>();
            this.messages.add(smsSendClientRequest);
        }

        public static MessageDTO of(List<SmsSendClientRequest> list, String from) {
            for (SmsSendClientRequest smsSendClientRequest : list) {
                smsSendClientRequest.setFrom(from);
            }
            return new MessageDTO(list);
        }

        public static MessageDTO of(SmsSendClientRequest smsSendClientRequest, String from) {
            smsSendClientRequest.setFrom(from);
            return new MessageDTO(smsSendClientRequest);
        }
    }


}
