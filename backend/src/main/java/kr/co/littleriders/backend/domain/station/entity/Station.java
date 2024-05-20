package kr.co.littleriders.backend.domain.station.entity;

import jakarta.persistence.*;
import kr.co.littleriders.backend.application.dto.request.StationRequest;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "station",
uniqueConstraints = @UniqueConstraint(
        name = "station_latitude_longitude_unique",
        columnNames = {"academy_id","latitude","longitude"}
))

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 정류장 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id")
    private Academy academy; // 학원

    @Column(name = "name",nullable = false)
    private String name; // 정류장명

    @Column(name = "latitude",nullable = false)
    private double latitude; // 위도

    @Column(name = "longitude",nullable = false)
    private double longitude; // 경도

    private Station(final Academy academy, String name, Double latitude, Double longitude) {
        this.academy = academy;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Station of(final Academy academy, String name, Double latitude, Double longitude) {
        return new Station(
                academy,
                name,
                latitude,
                longitude
        );
    }

    public void update(StationRequest stationRequest) {
        this.name = stationRequest.getName();
        this.latitude = stationRequest.getLatitude();
        this.longitude = stationRequest.getLongitude();
    }
}
