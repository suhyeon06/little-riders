package kr.co.littleriders.backend.application.facade.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import kr.co.littleriders.backend.application.dto.response.AcademyTeacherResponse;
import kr.co.littleriders.backend.domain.teacher.entity.TeacherStatus;
import kr.co.littleriders.backend.domain.teacher.error.code.TeacherErrorCode;
import kr.co.littleriders.backend.domain.teacher.error.exception.TeacherException;
import kr.co.littleriders.backend.global.utils.ImageUtil;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.co.littleriders.backend.application.dto.request.TeacherRegistRequest;
import kr.co.littleriders.backend.application.facade.AcademyTeacherFacade;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.teacher.TeacherService;
import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class AcademyTeacherFacadeImpl implements AcademyTeacherFacade {

	private final TeacherService teacherService;
	private final AcademyService academyService;
	private final ImageUtil imageUtil;

	@Override
	@Transactional
	public Long insertTeacher(TeacherRegistRequest teacherRegistRequest, Long academyId) {

		Academy academy = academyService.findById(academyId);
		Teacher teacher = teacherRegistRequest.toEntity(academy);

		MultipartFile image = teacherRegistRequest.getImage();
		if (image != null) {
			String imagePath = imageUtil.saveImage(image);
			teacher.setImagePath(imagePath);
		}

		return teacherService.save(teacher);
	}

	@Override
	public List<AcademyTeacherResponse> readTeacherList(Long academyId) {

		Academy academy = academyService.findById(academyId);
		return teacherService.findByAcademy(academy)
							.stream()
							.sorted(Comparator.comparing(teacher -> teacher.getStatus() == TeacherStatus.WORK ? 0 : 1))
							.map(AcademyTeacherResponse::from)
							.collect(Collectors.toList());

	}

}
