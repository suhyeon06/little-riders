package kr.co.littleriders.backend.application.controller;

import kr.co.littleriders.backend.application.dto.response.AcademyLocationResponse;
import kr.co.littleriders.backend.application.facade.AcademyFacade;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/academy")
@RequiredArgsConstructor
public class AcademyController {

    private final AcademyFacade academyFacade;

    @GetMapping("/address")
    public ResponseEntity<AcademyLocationResponse> getAcademyAddress(@Auth AuthAcademy authAcademy) {

        Long academyId = authAcademy.getId();
        AcademyLocationResponse academyLocation = academyFacade.readAcademyAddress(academyId);

        return ResponseEntity.ok().body(academyLocation);
    }
}
