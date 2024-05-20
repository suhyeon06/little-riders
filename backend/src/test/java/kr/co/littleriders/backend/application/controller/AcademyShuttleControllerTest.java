package kr.co.littleriders.backend.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.littleriders.backend.application.dto.request.ShuttleRegistRequest;
import kr.co.littleriders.backend.application.dto.response.AcademyShuttleResponse;
import kr.co.littleriders.backend.common.fixture.AcademyFixture;
import kr.co.littleriders.backend.common.fixture.ShuttleFixture;
import kr.co.littleriders.backend.common.fixture.TerminalFixture;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.shuttle.ShuttleService;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleStatus;
import kr.co.littleriders.backend.domain.terminal.ShuttleTerminalAttachService;
import kr.co.littleriders.backend.domain.terminal.TerminalService;
import kr.co.littleriders.backend.domain.terminal.entity.ShuttleTerminalAttach;
import kr.co.littleriders.backend.domain.terminal.entity.Terminal;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AcademyShuttleControllerTest {

	@Autowired
	private AcademyService academyService;

	@Autowired
	private ShuttleService shuttleService;

	@Autowired
	private TerminalService terminalService;

	@Autowired
	private ShuttleTerminalAttachService shuttleTerminalAttachService;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Nested
	@DisplayName("셔틀 추가 기능")
	class addShuttle {

		@Test
		@DisplayName("성공")
		void whenSuccess() throws Exception {

			Academy academy = AcademyFixture.BOXING.toAcademy();
			academyService.save(academy);
			ShuttleRegistRequest regist = ShuttleFixture.HO_01.toShuttleRegistRequest();
			Shuttle shuttle = regist.toEntity(academy);
			Long driverId = shuttleService.save(shuttle);

			mockMvc.perform(
					post("/admin/shuttle")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(regist))
				)
				.andExpect(status().isOk())
				.andExpect(content().string(String.valueOf(driverId)))
				.andDo(print());

		}
	}

	@Nested
	@DisplayName("셔틀 목록 조회 기능")
	class getShuttleList {

		@Test
		@DisplayName("성공")
		void whenSuccess() throws Exception {

			// 학원 생성
			Academy academy = AcademyFixture.SOCCER.toAcademy();
			academyService.save(academy);


			Shuttle shuttle = ShuttleFixture.HO_02.toShuttle(academy,ShuttleStatus.USE);
			shuttleService.save(shuttle);

			Terminal terminal = TerminalFixture.A.toTerminal(academy);
			terminalService.save(terminal);
			ShuttleTerminalAttach shuttleTerminalAttach = ShuttleTerminalAttach.of(shuttle, terminal);
			shuttleTerminalAttachService.save(shuttleTerminalAttach);

			Shuttle shuttle1 = ShuttleFixture.HO_19.toShuttle(academy,ShuttleStatus.REPAIRING);
			shuttleService.save(shuttle1);

			Terminal terminal1 = TerminalFixture.B.toTerminal(academy);
			terminalService.save(terminal1);
			ShuttleTerminalAttach shuttleTerminalAttach1 = ShuttleTerminalAttach.of(shuttle1, terminal1);
			shuttleTerminalAttachService.save(shuttleTerminalAttach1);


			Shuttle shuttle2 = ShuttleFixture.HO_21.toShuttle(academy,ShuttleStatus.USE);
			shuttleService.save(shuttle2);

			Terminal terminal2 = TerminalFixture.C.toTerminal(academy);
			terminalService.save(terminal2);
			ShuttleTerminalAttach shuttleTerminalAttach2 = ShuttleTerminalAttach.of(shuttle2, terminal2);
			shuttleTerminalAttachService.save(shuttleTerminalAttach2);


			List<AcademyShuttleResponse> shuttleList = new ArrayList<>();
			AcademyShuttleResponse shuttleResponse = AcademyShuttleResponse.from(shuttle);
			AcademyShuttleResponse shuttleResponse1 = AcademyShuttleResponse.from(shuttle1);
			AcademyShuttleResponse shuttleResponse2 = AcademyShuttleResponse.from(shuttle2);
			shuttleList.add(shuttleResponse);
			shuttleList.add(shuttleResponse2);
			shuttleList.add(shuttleResponse1);


			mockMvc.perform(
							get("/academy/shuttle")
					)
					.andExpect(status().isOk())
					.andExpect(content().json(objectMapper.writeValueAsString(shuttleList)))
					.andDo(print());

		}
	}

}