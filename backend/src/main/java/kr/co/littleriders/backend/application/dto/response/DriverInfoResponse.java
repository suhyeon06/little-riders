package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.domain.driver.entity.Driver;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DriverInfoResponse {
    private final long id;
    private final String name;
    private final String phoneNumber;
    private final String image;

    public static DriverInfoResponse from(Driver driver){
        return new DriverInfoResponse(
                driver.getId(),
                driver.getName(),
                driver.getPhoneNumber(),
                driver.getImagePath()
        );
    }

}
