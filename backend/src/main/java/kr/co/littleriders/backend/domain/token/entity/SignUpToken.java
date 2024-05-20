package kr.co.littleriders.backend.domain.token.entity;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.UUID;

@RedisHash(value = "sign_up_token")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SignUpToken {

    @Id
    private String email;


    @Getter
    private String token;


    @Enumerated(EnumType.STRING)
    private SignUpTokenType type;

    @TimeToLive
    private long ttl;

    private SignUpToken(String email, SignUpTokenType type) {
        this.email = email;
        this.token = UUID.randomUUID().toString();
        this.type = type;
        this.ttl = 30 * 60;
    }

    public static SignUpToken of(String email, SignUpTokenType type) {

        return new SignUpToken(email, type);

    }

    public boolean equalsToken(String token) {
        return this.token.equals(token);
    }

    public boolean equalsType(SignUpTokenType type) {
        return this.type.equals(type);
    }

    public boolean notEqualsType(SignUpTokenType signUpTokenType) {
        return !this.equalsType(signUpTokenType);
    }

    public boolean notEqualsToken(String token) {
        return !this.equalsToken(token);
    }
}
