package kr.co.littleriders.backend.domain.token.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@RedisHash(value = "refresh_token")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RefreshToken {

    @Id
    private String token;

    @TimeToLive
    public long ttl;

    private RefreshToken(final String token, final long ttl) {
        this.token = token;
        this.ttl = ttl;
    }

    public static RefreshToken of(final String token, final long ttl) {
        return new RefreshToken(token, ttl);
    }
}
