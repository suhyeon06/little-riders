package kr.co.littleriders.backend.domain.terminal.entity;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "terminal")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Terminal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 단말기 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id")
    private Academy academy; // 학원

    @Column(name = "terminal_number",unique = true)
    private String terminalNumber; // 단말기 고유번호

    @OneToOne(mappedBy = "terminal")
    private ShuttleTerminalAttach shuttleTerminalAttach; // 차량 단말기 부착 정보
    private Terminal(Academy academy, String terminalNumber){
        //TODO : terminalNumber 에 대한 VALID CHECK 추가
        this.academy = academy;
        this.terminalNumber = terminalNumber;
    }
    public static Terminal of(Academy academy, String terminalNumber) {
        return new Terminal(academy,terminalNumber);
    }
}
