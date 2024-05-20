package kr.co.littleriders.backend.application.controller;


import jakarta.validation.Valid;
import kr.co.littleriders.backend.application.dto.request.AcademyTerminalRegisterRequest;
import kr.co.littleriders.backend.application.facade.AcademyTerminalFacade;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/academy/terminal")
@RequiredArgsConstructor
public class AcademyTerminalController {

    private final AcademyTerminalFacade academyTerminalFacade;


    @PostMapping("")
    public ResponseEntity<Void> registerTerminal(@Auth AuthAcademy authAcademy, @Valid @RequestBody AcademyTerminalRegisterRequest academyTerminalRegisterRequest) {
        academyTerminalFacade.registerTerminal(authAcademy.getId(), academyTerminalRegisterRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
