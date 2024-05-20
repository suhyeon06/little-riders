//package kr.co.littleriders.backend.application.facade;
//
//import kr.co.littleriders.backend.application.dto.request.ShuttleChildRideRequest;
//import kr.co.littleriders.backend.application.dto.request.ShuttleLocationRequest;
//import kr.co.littleriders.backend.application.dto.request.ShuttleStartRequest;
//import kr.co.littleriders.backend.application.dto.response.DriverInfoResponse;
//import kr.co.littleriders.backend.application.dto.response.TeacherInfoResponse;
//import kr.co.littleriders.backend.common.fixture.*;
//import kr.co.littleriders.backend.domain.academy.AcademyChildService;
//import kr.co.littleriders.backend.domain.academy.AcademyService;
//import kr.co.littleriders.backend.domain.academy.entity.*;
//import kr.co.littleriders.backend.domain.driver.DriverService;
//import kr.co.littleriders.backend.domain.driver.entity.Driver;
//import kr.co.littleriders.backend.domain.driver.entity.DriverStatus;
//import kr.co.littleriders.backend.domain.route.RouteService;
//import kr.co.littleriders.backend.domain.route.entity.Route;
//import kr.co.littleriders.backend.domain.shuttle.ShuttleDriveService;
//import kr.co.littleriders.backend.domain.shuttle.ShuttleLocationService;
//import kr.co.littleriders.backend.domain.shuttle.ShuttleService;
//import kr.co.littleriders.backend.domain.shuttle.entity.*;
//import kr.co.littleriders.backend.domain.shuttle.error.exception.ShuttleChildRideException;
//import kr.co.littleriders.backend.domain.shuttle.error.exception.ShuttleDriveException;
//import kr.co.littleriders.backend.domain.shuttle.error.exception.ShuttleLocationException;
//import kr.co.littleriders.backend.domain.teacher.TeacherService;
//import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
//import kr.co.littleriders.backend.domain.teacher.entity.TeacherStatus;
//import kr.co.littleriders.backend.domain.terminal.TerminalService;
//import kr.co.littleriders.backend.domain.terminal.entity.Terminal;
//import kr.co.littleriders.backend.global.auth.dto.AuthTerminal;
//import kr.co.littleriders.backend.global.entity.Gender;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//        @Test
//        @DisplayName("성공")
//        void  whenSuccess(){
//
//            //given
//            Driver driver = DriverFixture.YOON.toDriver(academy,DriverStatus.WORK);
//            driverService.save(driver);
//            String cardNumber = driver.getCardNumber();
//
//
//            //when
//            DriverInfoResponse driverInfoResponse = shuttleFacade.getDriverInfoByCardNumber(authTerminal,cardNumber);
//
//
//
//            //then
//            assertEquals(driverInfoResponse.getId(), driver.getId());
//            assertEquals(driverInfoResponse.getName(),driver.getName());
//            assertEquals(driverInfoResponse.getImage(),driver.getImagePath());
//            assertEquals(driverInfoResponse.getPhoneNumber(),driver.getPhoneNumber());
//
//        }
//    }
//
//
//    @Nested
//    @DisplayName("선생님 정보 QR 조회")
//    class getTeacherInfoByCardNumber{
//
//        @Test
//        @DisplayName("성공")
//        void  whenSuccess(){
//
//            //given
//            Teacher teacher = TeacherFixture.NAM.toTeacher(academy,TeacherStatus.WORK);
//            teacherService.save(teacher);
//            String cardNumber = teacher.getCardNumber();
//
//
//            //when
//            TeacherInfoResponse teacherInfoResponse = shuttleFacade.getTeacherInfoByCardNumber(authTerminal,cardNumber);
//
//
//
//            //then
//            assertEquals(teacherInfoResponse.getId(), teacher.getId());
//            assertEquals(teacherInfoResponse.getName(),teacher.getName());
//            assertEquals(teacherInfoResponse.getImage(),teacher.getImagePath());
//            assertEquals(teacherInfoResponse.getPhoneNumber(),teacher.getPhoneNumber());
//
//        }
//    }
//    @Nested
//    @DisplayName("운행 가능 노선 목록 조회")
//    class getRouteList {
//
//        @Test
//        @DisplayName("성공")
//        void whenSuccess() throws Exception {
//            shuttleFacade.getRouteList(authTerminal);
//        }
//    }




//    @Nested
//    @DisplayName("운행 시작 테스트")
//    class startDriveTest {
//
//        @Test
//        @DisplayName("성공")
//        void whenSuccess() throws Exception {
//            AcademyFixture academyFixture = AcademyFixture.BOXING;
//            Academy academy = academyFixture.toAcademy();
//            TerminalFixture terminalFixture = TerminalFixture.A;
//            Terminal terminal = terminalFixture.toTerminal(academy);
//            ShuttleFixture shuttleFixture = ShuttleFixture.HO_01;
//            Shuttle shuttle = shuttleFixture.toShuttle(academy, ShuttleStatus.USE);
//            AuthTerminal authTerminal = AuthTerminal.of(terminal, shuttle);
//
//            RouteFixture routeFixture = RouteFixture.A;
//            Route route = routeFixture.toRoute(academy, "board");
//            routeService.save(route);
//            DriverFixture driverFixture = DriverFixture.YOON;
//            Driver driver = driverFixture.toDriver(academy, DriverStatus.WORK);
//            driverService.save(driver);
//            TeacherFixture teacherFixture = TeacherFixture.CHA;
//            Teacher teacher = teacherFixture.toTeacher(academy, TeacherStatus.WORK);
//            teacherService.save(teacher);
//
//            ShuttleStartRequest shuttleStartRequest = new ShuttleStartRequest(route.getId(), driver.getId(), teacher.getId());
//            shuttleFacade.startDrive(authTerminal, shuttleStartRequest);


            //TODO - 이수현 - 탑승할 원생 리스트 테스트
//            AcademyChildFixture academyChildFixture1 = AcademyChildFixture.CHOI;
//            AcademyChildFixture academyChildFixture2 = AcademyChildFixture.KANG;
//            AcademyChildFixture academyChildFixture3 = AcademyChildFixture.KIM;
//            AcademyChildFixture academyChildFixture4 = AcademyChildFixture.PARK;

//        }
//    }


//    @Nested
//    @DisplayName("원생 승하차")
//    class recordChildRiding {
//
//        @Test
//        @DisplayName("성공")
//        void whenSuccess() throws Exception {
//            Family family = Family.of("a123@gmail.com", "1234", "이름", "주소", "010-2222-2222");
//            familyService.save(family);
//            Child child = Child.of("아이", LocalDate.parse("2000-01-01"), Gender.MALE, family);
//            childService.save(child);
//            AcademyFamily academyFamily = AcademyFamily.of(family, academy, AcademyFamilyStatus.AVAIL);
//            academyFamilyService.save(academyFamily);
//            AcademyChild academyChild = AcademyChild.of(child, academy, academyFamily, AcademyChildStatus.ATTENDING, CardType.BARCODE);
//            academyChildService.save(academyChild);
//
//            ShuttleChildRideRequest shuttleChildRideRequest = new ShuttleChildRideRequest(academyChild.getCardNumber(), 23.4, 22.2);
//            shuttleFacade.recordChildRiding(authTerminal, shuttleChildRideRequest);
//        }
//    }
//
//    @Nested
//    @DisplayName("위도 경도 업로드")
//    class uploadLocation {
//
//        @Test
//        @DisplayName("성공")
//        void whenSuccess() throws Exception {
//            ShuttleLocationRequest shuttleLocationRequest = new ShuttleLocationRequest(33.3, 45.2, 75);
//            shuttleFacade.uploadLocation(authTerminal, shuttleLocationRequest);
//        }
//    }
//
//    @Nested
//    @DisplayName("endDrive 테스트")
//    class endDrive{
//
//        @Test
//        @DisplayName("성공")
//        void whenSuccess(){
//            //given
//            Academy academy = AcademyFixture.BOXING.toAcademy();
//            academyService.save(academy);
//
//            Shuttle shuttle = ShuttleFixture.HO_11.toShuttle(academy,ShuttleStatus.USE);
//            shuttleService.save(shuttle);
//
//            Driver driver = DriverFixture.YOON.toDriver(academy,DriverStatus.WORK);
//            driverService.save(driver);
//
//            Teacher teacher = TeacherFixture.NAM.toTeacher(academy,TeacherStatus.WORK);
//
//            teacherService.save(teacher);
//
//
//            long shuttleId = shuttle.getId();
//            long teacherId = teacher.getId();
//            long driverId = driver.getId();
//
//            double latitude = 34.123;
//            double longitude = 126.123;
//            int speed = 14;
//            ShuttleLocationHistory shuttleLocationHistory = ShuttleLocationHistory.of(shuttleId,latitude,longitude,speed);
//            ShuttleDrive shuttleDrive = ShuttleDrive.of(shuttleId,1,driverId,teacherId);
//            ShuttleChildRide shuttleChildRide = ShuttleChildRide.of(shuttleId,1,latitude,longitude);
//            ShuttleLocation shuttleLocation = ShuttleLocation.of(shuttleId,latitude,longitude,speed);
//
//            shuttleLocationService.save(shuttleLocation);
//            shuttleLocationHistoryService.save(shuttleLocationHistory);
//            shuttleDriveService.save(shuttleDrive);
//            shuttleChildRideService.save(shuttleChildRide);
//
//            //when
//            shuttleFacade.endDrive(shuttleId);
//            //then
//
//            assertThrows(ShuttleLocationException.class,
//                    () -> shuttleLocationService.findByShuttleId(shuttleId));
//
//            assertThrows(ShuttleLocationHistoryException.class,
//                    () -> shuttleLocationHistoryService.findByShuttleId(shuttleId));
//
//            assertThrows(ShuttleDriveException.class,
//                    () -> shuttleDriveService.findByShuttleId(shuttleId));
//
//            assertThrows(ShuttleChildRideException.class,
//                    () -> shuttleChildRideService.findByShuttleId(shuttleId));
//
//
//
//        }
//
//    }
//}
