package kr.co.littleriders.backend.application.controller;


import kr.co.littleriders.backend.application.facade.SseFacade;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/academy/connection")
@RequiredArgsConstructor
public class AcademySseController {

    private final SseFacade sseFacade;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> subscribe(@Auth AuthAcademy authAcademy) {

        SseEmitter sseEmitter = sseFacade.createAcademySseConnectionByAcademyId(authAcademy.getId());
        return ResponseEntity.ok(sseEmitter);
    }
}
