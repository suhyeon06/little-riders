package kr.co.littleriders.backend.application.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.littleriders.backend.application.dto.response.ShuttleDailyHistoryResponse;
import kr.co.littleriders.backend.application.dto.response.ShuttleDetailHistoryResponse;
import kr.co.littleriders.backend.application.facade.HistoryFacade;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/academy/history")
@RequiredArgsConstructor
public class AcademyHistoryController {

	private final HistoryFacade histroyFacade;

	@GetMapping("/shuttle/{shuttleId}")
	public ResponseEntity<List<LocalDateTime>> getShuttleRunningDate(@Auth AuthAcademy authAcademy,
																 	@PathVariable(value = "shuttleId") Long shuttleId) {

		Long academyId = authAcademy.getId();
		List<LocalDateTime> dayList = histroyFacade.readShuttleDateList(shuttleId, academyId);

		return ResponseEntity.ok().body(dayList);
	}

	@GetMapping("/shuttle")
	public ResponseEntity<List<ShuttleDailyHistoryResponse>> getShuttleDailyHistory(@Auth AuthAcademy authAcademy,
																					@RequestParam(value = "shuttleId") Long shuttleId,
																					@RequestParam(value = "date") LocalDate date) {

		Long academyId = authAcademy.getId();
		List<ShuttleDailyHistoryResponse> shuttleDailyHistoryList = histroyFacade.readShuttleDailyHistory(academyId, shuttleId, date);

		return ResponseEntity.ok().body(shuttleDailyHistoryList);
	}

	@GetMapping("/{historyId}")
	public ResponseEntity<ShuttleDetailHistoryResponse> getShuttleDetailHistory(@Auth AuthAcademy authAcademy,
																				@PathVariable(value = "historyId") String historyId) {

		ShuttleDetailHistoryResponse shuttleDetailHistory = histroyFacade.readShuttleDetailHistory(historyId);

		return ResponseEntity.ok().body(shuttleDetailHistory);
	}
}
