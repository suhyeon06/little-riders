package kr.co.littleriders.backend.domain.verification.entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@RedisHash(value = "verification")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public final class Verification {

    @Id
    private String email;

    private String code;

    @TimeToLive
    private long ttl;

    @Enumerated(EnumType.STRING)
    private VerificationType type;


    private Verification(final String email, VerificationType type) {
        this(email, Verification.generateCode(), type);
    }

    private Verification(final String email, String code, VerificationType verificationType) {

        String regex = "^\\d{6}$";
        // 정규식 패턴에 매칭되는지 확인
        if (!code.matches(regex)) {
            throw new RuntimeException("Compile Level Error");
        }
        this.email = email;
        this.code = code;
        this.ttl = 60 * 3;
        this.type = verificationType;
    }

    private static String generateCode() {
        java.util.Random generator = new java.util.Random();
        generator.setSeed(System.currentTimeMillis());
        return String.format("%06d", generator.nextInt(100000, 1000000));
    }

    public static Verification of(final String email, final VerificationType type) {
        return new Verification(email, type);
    }

    public static Verification of(final String email, final String code, final VerificationType type) {
        return new Verification(email, code, type);
    }

    public boolean equalsCode(final String email) {
        return this.code.equals(email);
    }

    public boolean notEqualsCode(final String email) {
        return !this.equalsCode(email);
    }

    public boolean equalsType(VerificationType verificationType) {
        return this.type == verificationType;
    }

    public boolean notEqualsType(VerificationType verificationType) {
        return !this.equalsType(verificationType);
    }
}
