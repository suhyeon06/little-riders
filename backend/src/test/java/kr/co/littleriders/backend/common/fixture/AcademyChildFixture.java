package kr.co.littleriders.backend.common.fixture;


import kr.co.littleriders.backend.application.dto.request.ChildRegistRequest;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChildStatus;
import kr.co.littleriders.backend.domain.beacon.entity.Beacon;
import kr.co.littleriders.backend.global.entity.Gender;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public enum AcademyChildFixture {


    KIM("김여자","경기 양주시 은현면 용암리 460-3", LocalDate.of(2014, 10, 7), Gender.FEMALE,"kim.png","김가네","010-1111-1111","김씨메모"),
    PARK("박남자","경기 성남시 분당구 새마을로177번길 81", LocalDate.of(2014, 10, 7), Gender.MALE,"park.png","박가네","010-2222-2222","박씨메모"),
    CHOI("최여자","경기도 구리시 인창2로 177", LocalDate.of(2021, 9, 19), Gender.FEMALE,"choi.png","최가네","010-3333-3333","최씨메모"),
    KANG("강남자","경기도 고양시 덕양구 혜음로 215", LocalDate.of(2023, 5, 1), Gender.MALE,"kang.png","강가네","010-4444-4444","강씨메모");


    private String name;
    private String address;
    private LocalDate birthDate;
    private Gender gender;
    private String imagePath;
    private String familyName;
    @Setter
    private String phoneNumber;
    private String memo;


    public ChildRegistRequest toChildRegistRequest() {
        return new ChildRegistRequest(name, birthDate, gender.name(), null);
    }

    public AcademyChild toAcademyChild(Academy academy, Beacon beacon, AcademyChildStatus status) {
        return AcademyChild.of(academy,name,address,birthDate,gender,imagePath,beacon,familyName,phoneNumber,status,memo);
    }

}
