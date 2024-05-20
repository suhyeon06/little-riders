package kr.co.littleriders.backend.domain.teacher.entity;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "teacher")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 선생님 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id")
    private Academy academy; // 학원

    @Column(name = "name", nullable = false)
    private String name; // 이름

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber; // 전화번호

    @Setter
    @Column(name = "image_path")
    private String imagePath; // 이미지 경로

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TeacherStatus status; // 상태

    @Column(name = "card_number")
    private String cardNumber; // 카드정보

    private Teacher(Academy academy, String name, String phoneNumber, TeacherStatus status, String imagePath, String code) {
        this.academy = academy;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.imagePath = imagePath;
        this.cardNumber = code;
    }

    public static Teacher of(String name, String phoneNumber, Academy academy, TeacherStatus status) {
        return new Teacher(academy
                        , name
                        , phoneNumber
                        , status
                        , null
                        , generateCode());
	}


    public static Teacher of(String name, String phoneNumber, Academy academy, String imagePath,TeacherStatus status) {
        return new Teacher(academy
                , name
                , phoneNumber
                , status
                , imagePath
                , generateCode());
    }

    private static String generateCode() {
        return UUID.randomUUID().toString();
    }

    public boolean equalsAcademy(Academy academy) {
        return this.academy.equals(academy);
    }
}
