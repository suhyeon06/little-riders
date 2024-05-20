package kr.co.littleriders.backend.application.dto.request;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShuttleRegistRequest {

	@NotBlank
	private String licenseNumber;

	@NotBlank
	private String type;

	@NotBlank
	private String name;

	private MultipartFile image;

	public Shuttle toEntity(Academy academy) {
		return Shuttle.of(this.licenseNumber, this.name, this.type, academy, ShuttleStatus.USE);
	}
}
