package kr.co.littleriders.backend.domain.routeinfo.entity;


import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.route.entity.Route;
import kr.co.littleriders.backend.domain.station.entity.Station;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "station_route",
uniqueConstraints = {
        @UniqueConstraint(
                name = "station_route_does_not_duplicate_in_field",
                columnNames = {"route_id","station_id","academy_id","visit_order"}
        )
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RouteStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 경로 정류장 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id",nullable = false)
    private Route route; // 경로

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id",nullable = false)
    private Academy academy; // 학원

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id",nullable = false)
    private Station station; // 정류장

    @Column(name = "visit_order" , nullable = false)
    private Integer visitOrder; // 방문 순서

    @OneToMany(mappedBy = "boardRouteStation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChildBoardDropInfo> childBoardInfoList = new ArrayList<>();

    private RouteStation(Route route, Academy academy, Station station, int visitOrder) {
        this.route = route;
        this.academy = academy;
        this.station = station;
        this.visitOrder = visitOrder;
    }

    public static RouteStation of(Route route, Academy academy, Station station, int visitOrder) {
        return new RouteStation(
                route,
                academy,
                station,
                visitOrder
        );
    }

    public void addChildBoardDropInfo(ChildBoardDropInfo childBoardDropInfo) {
        this.childBoardInfoList.add(childBoardDropInfo);
    }

    public void removeChildBoardDropInfo(ChildBoardDropInfo childBoardDropInfo) {
        this.childBoardInfoList.remove(childBoardDropInfo);
    }

    public void updateVisitOrder(int newOrder) {
        this.visitOrder = newOrder;
    }


}
