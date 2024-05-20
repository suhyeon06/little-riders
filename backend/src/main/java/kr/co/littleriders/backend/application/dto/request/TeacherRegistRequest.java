package kr.co.littleriders.backend.application.dto.request;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import kr.co.littleriders.backend.domain.teacher.entity.TeacherStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherRegistRequest {

	@NotBlank
	private String name;

	@NotBlank
	private String phoneNumber;

	private MultipartFile image;

	public Teacher toEntity(Academy academy) {
		return Teacher.of(this.name, this.phoneNumber, academy, TeacherStatus.WORK);
	}
}
