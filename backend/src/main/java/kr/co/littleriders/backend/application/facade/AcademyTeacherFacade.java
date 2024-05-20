package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.TeacherRegistRequest;
import kr.co.littleriders.backend.application.dto.response.AcademyTeacherResponse;
import kr.co.littleriders.backend.application.dto.response.TeacherDetailResponse;

import java.util.List;

public interface AcademyTeacherFacade {

	Long insertTeacher(TeacherRegistRequest teacherRegistRequest, Long academyId);

	List<AcademyTeacherResponse> readTeacherList(Long academyId);

	TeacherDetailResponse readTeacherDetail(Long academyId, Long teacherId);
}
