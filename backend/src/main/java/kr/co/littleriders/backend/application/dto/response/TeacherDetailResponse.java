package kr.co.littleriders.backend.application.dto.response;


import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeacherDetailResponse {

    private Long id;

    private String name;

    private String phoneNumber;

    private String imagePath;

    private String uuid;

    private String status;


    private TeacherDetailResponse(Long id, String name, String phoneNumber, String imagePath, String uuid, String status) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.imagePath = imagePath;
        this.uuid = uuid;
        this.status = status;
    }

    public static TeacherDetailResponse from(Teacher teacher) {
        return new TeacherDetailResponse(teacher.getId(),
                                        teacher.getName(),
                                        teacher.getPhoneNumber(),
                                        teacher.getImagePath(),
                                        teacher.getCardNumber(),
                                        teacher.getStatus().name());
    }
}
