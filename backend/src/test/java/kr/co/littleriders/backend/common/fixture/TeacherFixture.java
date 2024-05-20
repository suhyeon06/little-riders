package kr.co.littleriders.backend.common.fixture;


import kr.co.littleriders.backend.application.dto.request.TeacherRegistRequest;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import kr.co.littleriders.backend.domain.teacher.entity.TeacherStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)

@AllArgsConstructor
@Getter
public enum TeacherFixture {


    CHOO("추선생", "01000000000","teacher_choo.png"),
    PARK("박선생", "01011111111","teacher_park.png"),
    SHIN("신선생", "010222222222","teacher_shin.png"),
    CHOI("최선생", "010333333333","teacher_choi.png"),
    KIM("김선생", "01044444444","teacher_kim.png"),
    HONG("홍선생", "01055555555","teacher_hong.png"),
    CHA("차선생", "01066666666","teacher_cha.png"),
    NAM("남선생", "01077777777","teacher_nam"),
    HA("하선생", "01088888888","teacher_ha.png"),
    NAM_GOONG("남궁선생", "01099999999","teacher_nam_goong.png");


    private String name;
    private String phoneNumber;
    private String imagePath;

    public Teacher toTeacher(Academy academy, TeacherStatus status) {
        return Teacher.of(
                name,
                phoneNumber,
                academy,
                imagePath,
                status
        );
    }

    public TeacherRegistRequest toTeacherRegisterRequest() {
        return new TeacherRegistRequest(name, phoneNumber, null);
    }



}
