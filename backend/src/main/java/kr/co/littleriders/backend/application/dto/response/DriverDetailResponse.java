package kr.co.littleriders.backend.application.dto.response;


import kr.co.littleriders.backend.domain.driver.entity.Driver;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DriverDetailResponse {

    private Long id;

    private String name;

    private String phoneNumber;

    private String imagePath;

    private String uuid;

    private String status;


    private DriverDetailResponse(Long id, String name, String phoneNumber, String imagePath, String uuid, String status) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.imagePath = imagePath;
        this.uuid = uuid;
        this.status = status;
    }

    public static DriverDetailResponse from(Driver driver) {
        return new DriverDetailResponse(driver.getId()
                                        , driver.getName()
                                        , driver.getPhoneNumber()
                                        , driver.getImagePath()
                                        , driver.getCardNumber()
                                        , driver.getStatus().name());
    }
}
