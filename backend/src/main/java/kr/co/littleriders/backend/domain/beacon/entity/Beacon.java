package kr.co.littleriders.backend.domain.beacon.entity;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@Table(name = "beacon")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class Beacon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id; // 비콘id

	@Column(name = "uuid")
	private String uuid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "academy_id", nullable = false)
	private Academy academy; // 학원

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "academy_child_id")
	private AcademyChild academyChild; // 원생

	public void updateAcademyChild(AcademyChild academyChild) {
		this.academyChild = academyChild;
	}

	private Beacon(String uuid, Academy academy){
		this.uuid = uuid;
		this.academy = academy;
	}
	public static Beacon of(String uuid, Academy academy){
		return new Beacon(uuid,academy);
	}
}
