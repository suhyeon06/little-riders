package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AcademyLocationResponse {

    private double latitude;

    private double longitude;

    private String address;

    public static AcademyLocationResponse from(Academy academy) {
        return new AcademyLocationResponse(academy.getLatitude(),
                academy.getLongitude(), academy.getAddress());
    }
}
