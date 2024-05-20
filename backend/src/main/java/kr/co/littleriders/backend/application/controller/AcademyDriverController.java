package kr.co.littleriders.backend.application.controller;

import kr.co.littleriders.backend.application.dto.response.AcademyDriverResponse;
import kr.co.littleriders.backend.application.dto.response.DriverDetailResponse;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import kr.co.littleriders.backend.application.dto.request.DriverRegistRequest;
import kr.co.littleriders.backend.application.facade.AcademyDriverFacade;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/academy/driver")
@RequiredArgsConstructor
public class AcademyDriverController {

	private final AcademyDriverFacade academyDriverFacade;

	@PostMapping
	public ResponseEntity<Long> addDriver(@Auth AuthAcademy authAcademy,
										  @ModelAttribute @Valid DriverRegistRequest driverRegistRequest) {

		Long academyId = authAcademy.getId();

		Long driverId = academyDriverFacade.insertDriver(driverRegistRequest, academyId);

		return ResponseEntity.ok().body(driverId);
	}

	@GetMapping
	public ResponseEntity<List<AcademyDriverResponse>> getDriverList(@Auth AuthAcademy authAcademy) {

		Long academyId = authAcademy.getId();

		List<AcademyDriverResponse> driverList = academyDriverFacade.readDriverList(academyId);

		return ResponseEntity.ok().body(driverList);
	}

	@GetMapping("/{driverId}")
	public ResponseEntity<DriverDetailResponse> getDriverDetail(@Auth AuthAcademy authAcademy,
																@PathVariable(value = "driverId") Long driverId){

		Long academyId = authAcademy.getId();

		DriverDetailResponse driverDetail = academyDriverFacade.readDriverDetail(academyId, driverId);

		return ResponseEntity.ok().body(driverDetail);
	}

}
