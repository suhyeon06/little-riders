package kr.co.littleriders.backend.global.jwt;

import kr.co.littleriders.backend.global.entity.MemberType;
import lombok.Getter;


@Getter
public class JwtMemberInfo {
    private final long memberId;

    private final MemberType memberType;

    private JwtMemberInfo(long memberId, MemberType memberType) {
        this.memberId = memberId;
        this.memberType = memberType;
    }

    public static JwtMemberInfo of(long memberId, MemberType memberType) {
        return new JwtMemberInfo(memberId, memberType);
    }
}
