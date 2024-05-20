package kr.co.littleriders.backend.application.dto.request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChildStatus;
import kr.co.littleriders.backend.global.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAcademyChildRequest {

	private String name;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;

	private String gender;

	private String familyName;

	private String phoneNumber;

	private String address;

	private MultipartFile image;

	private String memo;

	public AcademyChild toAcademyChild(Academy academy, String imagePath, AcademyChildStatus status){
		return AcademyChild.of(academy, this.name, this.address, this.birthDate,
			Gender.valueOf(this.gender), imagePath, null,
			this.familyName, this.phoneNumber, status, this.memo);
	}
}
