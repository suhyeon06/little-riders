package kr.co.littleriders.backend.domain.terminal.entity;

import jakarta.persistence.*;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shuttle_terminal_attach")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ShuttleTerminalAttach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 차량 단말기 부착 정보

    @OneToOne
    @JoinColumn(name = "shuttle_id")
    private Shuttle shuttle; // 차량

    @OneToOne
    @JoinColumn(name = "terminal_id")
    private Terminal terminal; // 단말기

    private ShuttleTerminalAttach(Shuttle shuttle, Terminal terminal) {
        this.shuttle = shuttle;
        this.terminal = terminal;
    }

    public static ShuttleTerminalAttach of(Shuttle shuttle, Terminal terminal) {
        return new ShuttleTerminalAttach(shuttle, terminal);
    }
}
