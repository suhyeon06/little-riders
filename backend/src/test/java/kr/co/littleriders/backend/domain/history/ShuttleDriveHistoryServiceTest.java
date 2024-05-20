//package kr.co.littleriders.backend.domain.history;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import kr.co.littleriders.backend.domain.academy.AcademyService;
//import kr.co.littleriders.backend.domain.academy.entity.Academy;
//import kr.co.littleriders.backend.domain.driver.DriverService;
//import kr.co.littleriders.backend.domain.driver.entity.Driver;
//import kr.co.littleriders.backend.domain.driver.entity.DriverStatus;
//import kr.co.littleriders.backend.domain.history.entity.ShuttleDriveHistory;
//import kr.co.littleriders.backend.domain.shuttle.ShuttleService;
//import kr.co.littleriders.backend.domain.shuttle.dto.ShuttleLocationDTO;
//import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
//import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleStatus;
//import kr.co.littleriders.backend.domain.teacher.TeacherService;
//import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
//import kr.co.littleriders.backend.domain.teacher.entity.TeacherStatus;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@SpringBootTest
//@Slf4j
//class ShuttleDriveHistoryServiceTest {
//    @Autowired
//    ShuttleDriveHistoryService shuttleDriveHistoryService;
//
//    @Autowired
//    ShuttleService shuttleService;
//
//    @Autowired
//    AcademyService academyService;
//
//    @Autowired
//    DriverService driverService;
//
//    @Autowired
//    TeacherService teacherService;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//
//    @Nested
//    @DisplayName("findByShuttleIdAndStartAt 테스트")
//    class findByShuttleIdAndStartAt{
//
//        @Test
//        @DisplayName("성공")
//        void whenSuccess() throws JsonProcessingException {
//            LocalDateTime now = LocalDateTime.now();
//            int year = now.getYear();
//            int month = now.getMonthValue();
//            int day = now.getDayOfMonth();
//            log.info("year={},month={},day={}",year,month,day);
//            List<ShuttleDriveHistory> shuttleDriveHistoryList = shuttleDriveHistoryService.findByShuttleIdAndStartAt(1,year,month,day);
//            String out = objectMapper.writeValueAsString(shuttleDriveHistoryList);
//            log.info("out = {}",out);
//        }
//    }
//
//    @Nested
//    @DisplayName("findDistinctYearAndMonthAndDayList 테스트")
//    class findDistinctYearAndMonthAndDayList{
//
//        @Test
//        @DisplayName("성공")
//        void whenSuccess(){
//            List<LocalDateTime> dateList = shuttleDriveHistoryService.findDistinctYearAndMonthAndDayListByShuttleId(1);
//            log.info("response = {}",dateList);
//        }
//    }
//
//    @Nested
//    @DisplayName("save 테스트")
//    class save{
//
//        @Test
//        @DisplayName("성공")
//        void whenSucces() throws JsonProcessingException {
//            LocalDateTime start = LocalDateTime.now();
//            LocalDateTime end = LocalDateTime.of(2024,3,11,13,12);
//
//            Academy academy = Academy.of(
//                    "a",
//                    "b",
//                    "c",
//                    "d",
//                    "e",
//                    3,
//                    4
//            );
//            academyService.save(academy);
//            Shuttle shuttle = Shuttle.of(
//                    "가1234",
//                    "이름",
//                    "타입",
//                    academy,
//                    ShuttleStatus.USE
//            );
//            shuttleService.save(shuttle);
//
//            Driver driver = Driver.of(
//                    "기사",
//                    "전화번호",
//                    academy,
//                    DriverStatus.WORK
//            );
//            driverService.save(driver);
//            Teacher teacher = Teacher.of(
//                    "선생",
//                    "전화번호",
//                    academy,
//                    TeacherStatus.WORK
//            );
//
//            teacherService.save(teacher);
//
//            List<ShuttleLocationDTO> shuttleLocationDTOList = new ArrayList<>();
//            shuttleLocationDTOList.add( ShuttleLocationDTO.of(1,2,LocalDateTime.now()));
//            shuttleLocationDTOList.add( ShuttleLocationDTO.of(1,3,LocalDateTime.now()));
//            shuttleLocationDTOList.add( ShuttleLocationDTO.of(1,4,LocalDateTime.now()));
//            shuttleLocationDTOList.add( ShuttleLocationDTO.of(1,5,LocalDateTime.now()));
//
//            ShuttleDriveHistory shuttleDriveHistory = ShuttleDriveHistory.of(
//                    start,
//                    end,
//                    shuttle,
//                    driver,
//                    teacher,
//                    shuttleLocationDTOList
//            );
//
//            String savedId = shuttleDriveHistoryService.save(shuttleDriveHistory);
//
//            ShuttleDriveHistory found = shuttleDriveHistoryService.findById(savedId);
//            String s = objectMapper.writeValueAsString(found);
//
//            log.info("saved data -> [{}]",s);
//
//        }
//
//    }
//}
//
////    public static ShuttleDriveHistory of(LocalDateTime start, LocalDateTime end, Shuttle shuttle, Driver driver, Teacher teacher, List<ShuttleLocationDTO> shuttleLocationDTOList)
