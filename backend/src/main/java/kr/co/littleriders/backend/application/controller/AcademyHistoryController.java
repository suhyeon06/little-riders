package kr.co.littleriders.backend.application.controller;

import kr.co.littleriders.backend.application.dto.response.ShuttleDailyHistoryResponse;
import kr.co.littleriders.backend.application.dto.response.ShuttleDetailHistoryResponse;
import kr.co.littleriders.backend.application.dto.response.ShuttleHistoryDateResponse;
import kr.co.littleriders.backend.application.facade.HistoryFacade;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/academy/history")
@RequiredArgsConstructor
public class AcademyHistoryController {

	private final HistoryFacade histroyFacade;

	@GetMapping("/shuttle/{shuttleId}")
	public ResponseEntity<List<ShuttleHistoryDateResponse>> getShuttleRunningDate(@Auth AuthAcademy authAcademy,
																 	@PathVariable(value = "shuttleId") Long shuttleId) {

		Long academyId = authAcademy.getId();
		List<ShuttleHistoryDateResponse> dayList = histroyFacade.readShuttleDateList(shuttleId, academyId);

		return ResponseEntity.ok().body(dayList);
	}

	@GetMapping("/shuttle")
	public ResponseEntity<List<ShuttleDailyHistoryResponse>> getShuttleDailyHistory(@Auth AuthAcademy authAcademy,
																					@RequestParam(value = "shuttleId") Long shuttleId,
																					@RequestParam(value = "year") int year,
																					@RequestParam(value = "month") int month,
																					@RequestParam(value = "day") int day) {

		Long academyId = authAcademy.getId();

		List<ShuttleDailyHistoryResponse> shuttleDailyHistoryList = histroyFacade.readShuttleDailyHistory(academyId, shuttleId, year,month,day);

		return ResponseEntity.ok().body(shuttleDailyHistoryList);
	}

	@GetMapping("/{historyId}")
	public ResponseEntity<ShuttleDetailHistoryResponse> getShuttleDetailHistory(@Auth AuthAcademy authAcademy,
																				@PathVariable(value = "historyId") String historyId) {

		ShuttleDetailHistoryResponse shuttleDetailHistory = histroyFacade.readShuttleDetailHistory(historyId);

		return ResponseEntity.ok().body(shuttleDetailHistory);
	}
}
