package kr.co.littleriders.backend.application.controller;

import kr.co.littleriders.backend.application.dto.response.AcademyShuttleResponse;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import kr.co.littleriders.backend.application.dto.request.ShuttleRegistRequest;
import kr.co.littleriders.backend.application.facade.AcademyShuttleFacade;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/academy/shuttle")
@RequiredArgsConstructor
public class AcademyShuttleController {

	private final AcademyShuttleFacade academyShuttleFacade;

	@PostMapping
	public ResponseEntity<Long> addShuttle(@Auth AuthAcademy authAcademy,
										   @ModelAttribute @Valid ShuttleRegistRequest shuttleRegistRequest) {

		Long academyId = authAcademy.getId();

		Long shuttleId = academyShuttleFacade.insertShuttle(shuttleRegistRequest, academyId);

		return ResponseEntity.ok().body(shuttleId);
	}

	@GetMapping
	public ResponseEntity<List<AcademyShuttleResponse>> getShuttleList(@Auth AuthAcademy authAcademy) {

		Long academyId = authAcademy.getId();

		List<AcademyShuttleResponse> shuttleList = academyShuttleFacade.readShuttleList(academyId);

		return ResponseEntity.ok().body(shuttleList);
	}

}
