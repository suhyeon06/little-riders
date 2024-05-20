package kr.co.littleriders.backend.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.driver.entity.Driver;
import kr.co.littleriders.backend.domain.driver.entity.DriverStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverRegistRequest {

	@NotBlank
	private String name;

	@NotBlank
	private String phoneNumber;

	private MultipartFile image;

	public Driver toEntity(Academy academy) {
		return Driver.of(this.name, this.phoneNumber, academy, DriverStatus.WORK);
	}
}
