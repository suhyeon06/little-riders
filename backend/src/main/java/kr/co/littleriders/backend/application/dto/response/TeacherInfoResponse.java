package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TeacherInfoResponse {
    private final long id;
    private final String name;
    private final String phoneNumber;
    private final String image;

    public static TeacherInfoResponse from(Teacher teacher){
        return new TeacherInfoResponse(
                teacher.getId(),
                teacher.getName(),
                teacher.getPhoneNumber(),
                teacher.getImagePath()
        );
    }

}
