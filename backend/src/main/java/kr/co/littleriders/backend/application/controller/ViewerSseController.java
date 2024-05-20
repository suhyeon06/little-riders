package kr.co.littleriders.backend.application.controller;


import kr.co.littleriders.backend.application.facade.SseFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/viewer/connection")
@RequiredArgsConstructor
public class ViewerSseController {

    private final SseFacade sseFacade;


    @GetMapping(value = "/{uuid}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> subscribe(@PathVariable(name = "uuid") String uuid) {
        SseEmitter sseEmitter = sseFacade.createSmsUserSseConnectionByUuid(uuid);
        return ResponseEntity.ok(sseEmitter);
    }
}
