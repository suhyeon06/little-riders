package kr.co.littleriders.backend.domain.academy.entity;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.beacon.entity.Beacon;
import kr.co.littleriders.backend.domain.routeinfo.entity.ChildBoardDropInfo;
import kr.co.littleriders.backend.global.entity.Gender;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity @Getter
@Table(name = "academy_child")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
public class AcademyChild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 원생 정보 id

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "image_path")
    private String imagePath;

    @OneToOne(optional = true)
    @JoinColumn(name = "beacon_id")
    private Beacon beacon; // 카드 정보

    @Column(name = "family_name")
    private String familyName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AcademyChildStatus status; // 상태

    @Column(name = "memo")
    private String memo;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id", nullable = false)
    private Academy academy; // 학원

    @OneToMany(mappedBy = "academyChild")
    private List<ChildBoardDropInfo> childBoardDropInfoList; // 원생 승하차 목록
    //TODO- HOTFIX-이윤지 -
    //TODO- HOTFIX-이수현 - null 다른값 처리 필요함

    private AcademyChild(String name, String address, LocalDate birthDate, Gender gender, String imagePath, Beacon beacon, String familyName, String phoneNumber, AcademyChildStatus status, String memo, Academy academy) {
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.gender = gender;
        this.imagePath = imagePath;
        this.beacon = beacon;
        this.familyName = familyName;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.memo = memo;
        this.academy = academy;
    }

    public static AcademyChild of(Academy academy,
                                  String name,
                                  String address,
                                  LocalDate birthDate,
                                  Gender gender,
                                  String imagePath,
                                  Beacon beacon,
                                  String familyName,
                                  String phoneNumber,
                                  AcademyChildStatus status,
                                  String memo) {

        return new AcademyChild(
                name
                , address
                , birthDate
                , gender
                , imagePath
                , beacon
                , familyName
                , phoneNumber
                , status
                , memo, academy);
    }

    public void updateStatus(AcademyChildStatus status) {
        this.status = status;
    }

    public boolean equalsAcademy(Academy academy) {
        return this.academy.equals(academy);
    }

    public boolean isAttending() {
        return this.status.equals(AcademyChildStatus.ATTENDING);
    }

    public void detachBeacon() {
        this.beacon = null;
    }
}