package kr.co.littleriders.backend.application.dto.response;


import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AcademyTeacherResponse {

    private Long id;

    private String name;

    private String phoneNumber;

    private String imagePath;

    private String status;


    private AcademyTeacherResponse(Long id, String name, String phoneNumber, String imagePath, String status) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.imagePath = imagePath;
        this.status = status;
    }

    public static AcademyTeacherResponse from(Teacher teacher) {
        return new AcademyTeacherResponse(teacher.getId(),
                                          teacher.getName(),
                                          teacher.getPhoneNumber(),
                                          teacher.getImagePath(),
                                          teacher.getStatus().name());
    }
}
