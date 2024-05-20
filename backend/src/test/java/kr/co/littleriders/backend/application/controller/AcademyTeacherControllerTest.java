package kr.co.littleriders.backend.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.littleriders.backend.application.dto.request.TeacherRegistRequest;
import kr.co.littleriders.backend.application.dto.response.AcademyTeacherResponse;
import kr.co.littleriders.backend.common.fixture.AcademyFixture;
import kr.co.littleriders.backend.common.fixture.TeacherFixture;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.teacher.TeacherService;
import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import kr.co.littleriders.backend.domain.teacher.entity.TeacherStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AcademyTeacherControllerTest {

	@Autowired
	private AcademyService academyService;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Nested
	@DisplayName("선탑자 추가 기능")
	class addTeacher {

		@Test
		@DisplayName("성공")
		void whenSuccess() throws Exception {

			Academy academy = AcademyFixture.BOXING.toAcademy();
			academyService.save(academy);

			TeacherRegistRequest regist = TeacherFixture.HA.toTeacherRegisterRequest();
			Teacher teacher = regist.toEntity(academy);
			Long teacherId = teacherService.save(teacher);

			mockMvc.perform(
					post("/admin/teacher")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(regist))
				)
				.andExpect(status().isOk())
				.andExpect(content().string(String.valueOf(teacherId)))
				.andDo(print());

		}
	}

	@Nested
	@DisplayName("선탑자 목록 조회 기능")
	class getTeacherList {

		@Test
		@DisplayName("성공")
		void whenSuccess() throws Exception {


			Academy academy = AcademyFixture.BOXING.toAcademy();
			// 학원 생성
			academyService.save(academy);

			Teacher teacher = TeacherFixture.HA.toTeacher(academy,TeacherStatus.WORK);
			// 선탑자 생성
			teacherService.save(teacher);

			Teacher teacher1 = TeacherFixture.HONG.toTeacher(academy,TeacherStatus.RESIGN);
			teacherService.save(teacher1);

			Teacher teacher2 = TeacherFixture.NAM_GOONG.toTeacher(academy,TeacherStatus.WORK);
			teacherService.save(teacher2);

			List<AcademyTeacherResponse> teacherList = new ArrayList<AcademyTeacherResponse>();
			AcademyTeacherResponse teacherResponse = AcademyTeacherResponse.from(teacher);
			AcademyTeacherResponse teacherResponse1 = AcademyTeacherResponse.from(teacher1);
			AcademyTeacherResponse teacherResponse2 = AcademyTeacherResponse.from(teacher2);
			teacherList.add(teacherResponse);
			teacherList.add(teacherResponse2);
			teacherList.add(teacherResponse1);


			mockMvc.perform(
							get("/admin/teacher")
					)
					.andExpect(status().isOk())
					.andExpect(content().json(objectMapper.writeValueAsString(teacherList)))
					.andDo(print());

		}
	}
}