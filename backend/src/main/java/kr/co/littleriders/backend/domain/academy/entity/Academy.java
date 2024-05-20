package kr.co.littleriders.backend.domain.academy.entity;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.driver.entity.Driver;
import kr.co.littleriders.backend.domain.route.entity.Route;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.station.entity.Station;
import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import kr.co.littleriders.backend.domain.terminal.entity.Terminal;
import kr.co.littleriders.backend.global.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "academy")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Academy implements Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 학원id

    @Column(name = "email", nullable = false)
    private String email; // 이메일

    @Setter
    @Column(name = "password", nullable = false)
    private String password; // 비밀번호

    @Column(name = "name", nullable = false)
    private String name; // 학원이름

    @Column(name = "address", nullable = false)
    private String address; // 학원주소

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber; // 학원 전화번호

    @Column(name = "latitude",nullable = false)
    private double latitude;

    @Column(name = "longitude",nullable = false)
    private double longitude;

    @Column(name = "image_path")
    private String imagePath; // 이미지 경로

    @OneToMany(mappedBy = "academy")
    private List<Shuttle> shuttleList; // 차량 목록

    @OneToMany(mappedBy = "academy")
    private List<Driver> driverList; // 기사 목록

    @OneToMany(mappedBy = "academy")
    private List<Teacher> teacherList; // 선생님 목록

    @OneToMany(mappedBy = "academy")
    private List<Terminal> terminalList; // 단말기 목록

    @OneToMany(mappedBy = "academy")
    private List<Station> stationList; // 정류장 목록

    @OneToMany(mappedBy = "academy")
    private List<Route> routeList; // 경로 목록

    @OneToMany(mappedBy = "academy")
    private List<AcademyChild> academyChildList = new ArrayList<>(); // 원생정보 목록


    private Academy(String email, String password, String name, String address, String phoneNumber, double latitude, double longitude) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Academy of(String email, String password, String name, String address, String phoneNumber, double latitude, double longitude) {
        return new Academy(email, password, name, address, phoneNumber, latitude, longitude);
    }
}
