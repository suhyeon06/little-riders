package kr.co.littleriders.backend.application.facade.impl;

import kr.co.littleriders.backend.application.dto.response.ShuttleDailyHistoryResponse;
import kr.co.littleriders.backend.application.dto.response.ShuttleDetailHistoryResponse;
import kr.co.littleriders.backend.application.dto.response.ShuttleHistoryDateResponse;
import kr.co.littleriders.backend.application.facade.HistoryFacade;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.history.ShuttleDriveHistoryService;
import kr.co.littleriders.backend.domain.history.entity.ShuttleDriveHistory;
import kr.co.littleriders.backend.domain.shuttle.ShuttleService;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.shuttle.error.code.ShuttleErrorCode;
import kr.co.littleriders.backend.domain.shuttle.error.exception.ShuttleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HistoryFacadeImpl implements HistoryFacade {

    private final ShuttleDriveHistoryService shuttleDriveHistoryService;
    private final ShuttleService shuttleService;
    private final AcademyService academyService;

    @Override
    public List<ShuttleHistoryDateResponse> readShuttleDateList(Long shuttleId, Long academyId) {

        Academy academy = academyService.findById(academyId);
        Shuttle shuttle = shuttleService.findById(shuttleId);
        if (!shuttle.equalsAcademy(academy)) {
            throw ShuttleException.from(ShuttleErrorCode.FORBIDDEN);
        }

        return shuttleDriveHistoryService.findDistinctYearAndMonthAndDayListByShuttleId(shuttleId).stream().map(
                ShuttleHistoryDateResponse::from
        ).toList();
    }

    @Override
    public List<ShuttleDailyHistoryResponse> readShuttleDailyHistory(Long academyId, Long shuttleId, int year, int month, int day) {

        Academy academy = academyService.findById(academyId);
        Shuttle shuttle = shuttleService.findById(shuttleId);

        if (!shuttle.equalsAcademy(academy)) {
            throw ShuttleException.from(ShuttleErrorCode.FORBIDDEN);
        }

        return shuttleDriveHistoryService.findByShuttleIdAndStartAt(shuttleId,
                        year,
                        month,
                        day)
                .stream()
                .map(ShuttleDailyHistoryResponse::from)
                .toList();

    }

    @Override
    public ShuttleDetailHistoryResponse readShuttleDetailHistory(String historyId) {

        ShuttleDriveHistory shuttleDriveHistory = shuttleDriveHistoryService.findById(historyId);
        return ShuttleDetailHistoryResponse.from(shuttleDriveHistory);
    }
}
