package kr.co.littleriders.backend.domain.route.entity;

import jakarta.persistence.*;
import kr.co.littleriders.backend.application.dto.request.RouteRequest;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.routeinfo.entity.RouteStation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "route")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 경로 id


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id", nullable = false)
    private Academy academy; // 학원

    @Column(name = "name", nullable = false)
    private String name; // 경로명

    @Column(name = "type", nullable = false)
    private String type; // 경로 타입 - 등원(board). 하원(drop)

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RouteStation> routeStationList = new ArrayList<>(); // 정류장 목록

    private Route(final Academy academy, String name, String type) {
        this.academy = academy;
        this.name = name;
        this.type = type;
    }

    public static Route of(final Academy academy, String name, String type) {
        return new Route(
                academy,
                name,
                type
        );
    }


    public void update(RouteRequest routeRequest) {
        this.name = routeRequest.getName();
        this.type = routeRequest.getType();
    }

    public void addRouteStation(RouteStation routeStation) {
        this.routeStationList.add(routeStation);
    }

    public void removeRouteStation(RouteStation station) {
        this.routeStationList.remove(station);
    }
}
