package kr.co.littleriders.backend.domain.history.service;

import kr.co.littleriders.backend.domain.history.ShuttleDriveHistoryService;
import kr.co.littleriders.backend.domain.history.entity.ShuttleDriveHistory;
import kr.co.littleriders.backend.domain.history.error.code.ShuttleDriveHistoryErrorCode;
import kr.co.littleriders.backend.domain.history.error.exception.ShuttleDriveHistoryException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
class ShuttleDriveHistoryServiceImpl implements ShuttleDriveHistoryService {
    private final ShuttleDriveHistoryRepository shuttleDriveHistoryRepository;

    @Override
    public ShuttleDriveHistory findById(String id) {
        return shuttleDriveHistoryRepository.findById(id).orElseThrow(
                () -> ShuttleDriveHistoryException.from(ShuttleDriveHistoryErrorCode.NOT_FOUND)
        );
    }

    @Override
    public String save(ShuttleDriveHistory shuttleDriveHistory) {
        return shuttleDriveHistoryRepository.save(shuttleDriveHistory).getDocumentId();
    }

    @Override
    public List<ShuttleDriveHistory> findByShuttleIdAndStartAt(long shuttleId, int year, int month, int day) {

        LocalDate localDate = LocalDate.of(year,month,day);
        LocalDateTime startTime = localDate.atStartOfDay();
        LocalDateTime endTime = localDate.atTime(LocalTime.MAX);
        return shuttleDriveHistoryRepository.findByShuttle_IdAndStartBetween(shuttleId, startTime, endTime);
    }

    @Override
    public List<LocalDateTime> findDistinctYearAndMonthAndDayListByShuttleId(long shuttleId) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<String> dateStringList = shuttleDriveHistoryRepository.findDistinctDateListWithStringByShuttleId(shuttleId);
        return dateStringList.stream()
                .map(str -> LocalDate.parse(str, formatter).atStartOfDay()) // 각 문자열을 LocalDateTime으로 파싱
                .collect(Collectors.toList()); // 파싱된 LocalDateTime 객체들을 리스트로 수집
    }
}
