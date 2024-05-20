package kr.co.littleriders.backend.domain.routeinfo.entity;


import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.route.entity.Route;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "child_board_drop_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ChildBoardDropInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id; // 원생 승하차 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_child_id")
    private AcademyChild academyChild; // 원생 정보

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id")
    private Academy academy; // 학원

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_route_station_id",nullable = false)
    private RouteStation boardRouteStation; // 승차/하차 정류장

    private ChildBoardDropInfo(AcademyChild academyChild, Academy academy, RouteStation boardRouteStation) {
        this.academyChild = academyChild;
        this.academy = academy;
        this.boardRouteStation = boardRouteStation;
    }

    public static ChildBoardDropInfo of(AcademyChild academyChild, Academy academy, RouteStation boardRouteStation) {
        return new ChildBoardDropInfo(
                academyChild,
                academy,
                boardRouteStation
        );
    }


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "drop_route_station_id",nullable = false)
//    private RouteStation dropRouteStation; // 하차 정류장

//    @Enumerated(EnumType.STRING)
//    @Column(name = "day_of_the_week",nullable = false)
//    private ChildBoardDropInfoDayOfTheWeek dayOfTheWeek; // 요일
//
//    @CreatedDate
//    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
//    @Column(name = "board_time")
//    private LocalDateTime boardTime; // 승차 시간

}
