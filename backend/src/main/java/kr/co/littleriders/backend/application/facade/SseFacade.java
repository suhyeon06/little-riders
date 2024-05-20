package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.ShuttleLocationRequest;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleDrive;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface SseFacade {

    SseEmitter createSmsUserSseConnectionByUuid(String uuid);

    SseEmitter createAcademySseConnectionByAcademyId(long academyId);

    void broadcastShuttleLocationByShuttleId(long shuttleId, ShuttleLocationRequest shuttleLocationRequest);

    void broadcastBoardByAcademyIdAndViewerId(long shuttleId, long academyId, String viewerUuid, AcademyChild academyChild, double latitude, double longitude);

    void broadcastDropByAcademyIdAndViewerId(long shuttleId, long academyId, String viewerUuid, AcademyChild academyChild, double latitude, double longitude);

    void broadcastStartDriveByAcademyId(long academyId, ShuttleDrive shuttleDrive);

    void broadcastEndDriveByShuttleId(long shuttleId);
}