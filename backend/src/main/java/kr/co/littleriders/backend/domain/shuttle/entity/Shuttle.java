package kr.co.littleriders.backend.domain.shuttle.entity;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.terminal.entity.ShuttleTerminalAttach;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "shuttle",
    uniqueConstraints = @UniqueConstraint(
        name = "shuttle_unique",
        columnNames = {"academy_id","licenseNumber"}
    ))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Shuttle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 차량 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id")
    private Academy academy; // 학원

    @Column(name = "license_number", nullable = false)
    private String licenseNumber; // 차량번호

    @Column(name = "type")
    private String type; // 차종

    @Column(name = "name")
    private String name; // 차량명

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ShuttleStatus status; // 상태

    @Setter
    @Column(name = "image_path")
    private String imagePath; // 이미지 경로

    @OneToOne(mappedBy = "shuttle")
    private ShuttleTerminalAttach shuttleTerminalAttach; // 차량 단말기 부착 정보

    private Shuttle(String licenseNumber, String name, String type, Academy academy, String imagePath, ShuttleStatus status) {
        this.licenseNumber = licenseNumber;
        this.name = name;
        this.type = type;
        this.academy = academy;
        this.status = status;
        this.imagePath = imagePath;
    }

    public static Shuttle of(String licenseNumber, String name, String type, Academy academy, ShuttleStatus status) {
        return new Shuttle(licenseNumber
                        , name
                        , type
                        , academy
                        , null
                        , status);
    }

    public boolean equalsAcademy(Academy academy) {
        return this.academy.equals(academy);
    }
}
