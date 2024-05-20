package kr.co.littleriders.backend.global.entity;

import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import kr.co.littleriders.backend.global.auth.dto.AuthTerminal;
import lombok.Getter;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum MemberType {

    ACADEMY(AuthAcademy.class), TERMINAL(AuthTerminal.class);

    private final Class<?> DTOType;

    MemberType(Class<?> DTOType) {
        this.DTOType = DTOType;
    }

    private static final Map<Class<?>, MemberType> BY_DTOTYPE =
            Stream.of(values()).collect(Collectors.toMap(MemberType::getDTOType, e -> e));

    public static MemberType valueOf(Class<?> DTOType) {
        return BY_DTOTYPE.get(DTOType);
    }

}
