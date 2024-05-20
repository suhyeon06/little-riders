package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AcademyChildResponse {

    private Long academyChildId;

    private String name;

    private String birthDate;

    private String gender;

    private String imagePath;

    private String childStatus;

    private AcademyChildResponse(Long academyChildId, String name, String birthDate, String gender, String imagePath, String childStatus) {
        this.academyChildId = academyChildId;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.imagePath = imagePath;
        this.childStatus = childStatus;
    }

    public static AcademyChildResponse from(AcademyChild academyChild) {
        return new AcademyChildResponse(academyChild.getId()
                                        , academyChild.getName()
                                        , academyChild.getBirthDate().toString()
                                        , academyChild.getGender().name()
                                        , academyChild.getImagePath()
                                        , academyChild.getStatus().name());
    }

}
