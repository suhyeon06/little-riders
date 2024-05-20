package kr.co.littleriders.backend.application.dto.response;


import kr.co.littleriders.backend.domain.driver.entity.Driver;
import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AcademyDriverResponse {

    private Long id;

    private String name;

    private String phoneNumber;

    private String imagePath;

    private String status;


    private AcademyDriverResponse(Long id, String name, String phoneNumber, String imagePath, String status) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.imagePath = imagePath;
        this.status = status;
    }

    public static AcademyDriverResponse from(Driver driver) {
        return new AcademyDriverResponse(driver.getId()
                                        , driver.getName()
                                        , driver.getPhoneNumber()
                                        , driver.getImagePath()
                                        , driver.getStatus().name());
    }
}
