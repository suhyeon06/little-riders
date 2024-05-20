//package kr.co.littleriders.backend.application.facade;
//
//import kr.co.littleriders.backend.application.dto.request.RouteRequest;
//import kr.co.littleriders.backend.application.dto.request.RouteStationAcademyChildRequest;
//import kr.co.littleriders.backend.application.dto.request.RouteStationRequest;
//import kr.co.littleriders.backend.domain.academy.AcademyChildService;
//import kr.co.littleriders.backend.common.fixture.AcademyFixture;
//import kr.co.littleriders.backend.common.fixture.RouteFixture;
//import kr.co.littleriders.backend.domain.academy.AcademyService;
//import kr.co.littleriders.backend.domain.academy.entity.*;
//import kr.co.littleriders.backend.domain.route.RouteService;
//import kr.co.littleriders.backend.domain.route.entity.Route;
//import kr.co.littleriders.backend.domain.routeinfo.RouteStationService;
//import kr.co.littleriders.backend.domain.routeinfo.entity.RouteStation;
//import kr.co.littleriders.backend.domain.station.StationService;
//import kr.co.littleriders.backend.domain.station.entity.Station;
//import kr.co.littleriders.backend.domain.academy.entity.Academy;
//import kr.co.littleriders.backend.domain.route.error.code.RouteErrorCode;
//import kr.co.littleriders.backend.domain.route.error.exception.RouteException;
//import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
//import kr.co.littleriders.backend.global.entity.Gender;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class RouteFacadeTest {
//    @Autowired
//    private RouteFacade routeFacade;
//
//    @Autowired
//    private AcademyService academyService;
//
//    @Autowired
//    private RouteService routeService;
//
//    @Autowired
//    private StationService stationService;
//
//    @Autowired
//    private RouteStationService routeStationService;
//
//
//    @Autowired
//    private AcademyChildService academyChildService;
//
//
//
//    private Academy academy;
//
//    private AuthAcademy authAcademy;
//
//    @BeforeEach
//    void setUp() {
//        academy = AcademyFixture.BOXING.toAcademy();
//        academyService.save(academy);
//        authAcademy = AuthAcademy.from(academy);
//    }
//
//    @Nested
//    @DisplayName("노선 등록 테스트")
//    class createRoute {
//
//        @Test
//        @DisplayName("성공")
//        void whenSuccess() throws Exception {
//            //RouteRequest routeRequest = new RouteRequest("등원A", "board");
//            RouteRequest routeRequest = RouteFixture.F.toRouteRequest("board");
//            academyService.save(academy);
//            assertDoesNotThrow(() -> {
//                routeFacade.createRoute(authAcademy.getId(), routeRequest);
//            });
//        }
//
//        @Test
//        @DisplayName("실패, 중복된 정류장명")
//        void whenFailDuplicateName() {
//
//            //given
//            RouteFixture routeFixture = RouteFixture.R;
//            RouteRequest routeRequest = routeFixture.toRouteRequest("board");
//            academyService.save(academy);
//            Route route = routeFixture.toRoute(academy,"board");
//            routeService.save(route);
//
//
//            RouteException exception = assertThrows(RouteException.class,() -> { //when and then;
//                routeFacade.createRoute(authAcademy.getId(), routeRequest);
//            });
//            assertEquals(exception.getErrorCode(), RouteErrorCode.DUPLICATE_NAME);
//        }
//    }
//
//    @Nested
//    @DisplayName("노선 수정 테스트")
//    class updateRouteTest {
//        @Test
//        @DisplayName("성공")
//        void whenSuccess() throws Exception {
//
//            RouteRequest routeRequest = new RouteRequest("하원B", "drop");
//
//            Route route = Route.of(academy, "등원B", "board");
//            long routeId = routeService.save(route);
//
//            routeFacade.updateRoute(academy.getId(), routeId, routeRequest);
//        }
//    }
//
//    @Nested
//    @DisplayName("노선 삭제 테스트")
//    class deleteRouteTest {
//        @Test
//        @DisplayName("성공")
//        void whenSuccess() throws Exception {
//            Route route = Route.of(academy, "등원B", "board");
//            long routeId = routeService.save(route);
//
//            routeFacade.deleteRoute(academy.getId(), routeId);
//        }
//    }
//
//    @Nested
//    @DisplayName("노선별 정류장 추가 테스트")
//    class addRouteStation {
//
//        @Test
//        @DisplayName("성공")
//        void whenSuccess() throws Exception {
//            long academyId = academy.getId();
//
//            Route route = Route.of(academy, "등원A", "board");
//            long routeId = routeService.save(route);
//
//            Station station1 = Station.of(academy, "정류장A", 13.4, 13.5);
//            long stationId1 = stationService.save(station1);
//            Station station2 = Station.of(academy, "정류장B", 23.4, 23.5);
//            long stationId2 = stationService.save(station2);
//            Station station3 = Station.of(academy, "정류장C", 33.4, 33.5);
//            long stationId3 = stationService.save(station3);
//
//            List<RouteStationRequest> requestList = new ArrayList<>();
//            RouteStationRequest request1 = new RouteStationRequest(stationId2, 3);
//            RouteStationRequest request2 = new RouteStationRequest(stationId1, 1);
//            RouteStationRequest request3 = new RouteStationRequest(stationId3, 2);
//            requestList.add(request1);
//            requestList.add(request2);
//            requestList.add(request3);
//
//            routeFacade.addRouteStation(academyId, routeId, requestList);
//        }
//    }
//
//    @Nested
//    @DisplayName("노선별 정류장에 원생 추가 테스트")
//    class addAcademyChildToRouteStation {
//
//        @Test
//        @DisplayName("성공")
//        void whenSuccess() throws Exception {
//            long academyId = academy.getId();
//
//            Route route = Route.of(academy, "등원A", "board");
//            long routeId = routeService.save(route);
//
//            Station station1 = Station.of(academy, "정류장A", 13.4, 13.5);
//            long stationId1 = stationService.save(station1);
//            Station station2 = Station.of(academy, "정류장B", 23.4, 23.5);
//            long stationId2 = stationService.save(station2);
//
//            Family family1 = Family.of("111@gmail.com", "1234", "이름", "주소", "010-1111-1111");
//            familyService.save(family1);
//            Child child1 = Child.of("아이1", LocalDate.parse("2000-01-01"), Gender.MALE, family1);
//            childService.save(child1);
//            AcademyFamily academyFamily1 = AcademyFamily.of(family1, academy, AcademyFamilyStatus.AVAIL);
//            academyFamilyService.save(academyFamily1);
//            AcademyChild academyChild1 = AcademyChild.of(child1, academy, academyFamily1, AcademyChildStatus.ATTENDING, CardType.BARCODE);
//            academyChildService.save(academyChild1);
//
//            Family family2 = Family.of("222@gmail.com", "1234", "이름", "주소", "010-2222-2222");
//            familyService.save(family2);
//            Child child2 = Child.of("아이2", LocalDate.parse("2000-01-01"), Gender.MALE, family2);
//            childService.save(child2);
//            AcademyFamily academyFamily2 = AcademyFamily.of(family2, academy, AcademyFamilyStatus.AVAIL);
//            academyFamilyService.save(academyFamily2);
//            AcademyChild academyChild2 = AcademyChild.of(child2, academy, academyFamily2, AcademyChildStatus.ATTENDING, CardType.BARCODE);
//            academyChildService.save(academyChild2);
//
//            Family family3 = Family.of("333@gmail.com", "1234", "이름", "주소", "010-3333-3333");
//            familyService.save(family3);
//            Child child3 = Child.of("아이3", LocalDate.parse("2000-01-01"), Gender.MALE, family3);
//            childService.save(child3);
//            AcademyFamily academyFamily3 = AcademyFamily.of(family3, academy, AcademyFamilyStatus.AVAIL);
//            academyFamilyService.save(academyFamily3);
//            AcademyChild academyChild3 = AcademyChild.of(child3, academy, academyFamily3, AcademyChildStatus.ATTENDING, CardType.BARCODE);
//            academyChildService.save(academyChild3);
//
//            RouteStation routeStation1 = RouteStation.of(route, academy, station1, 1);
//            routeStationService.save(routeStation1);
//            RouteStation routeStation2 = RouteStation.of(route, academy, station2, 2);
//            routeStationService.save(routeStation2);
//
//            List<Long> academyChildIdList1 = new ArrayList<>();
//            academyChildIdList1.add(academyChild1.getId());
//            academyChildIdList1.add(academyChild2.getId());
//
//            List<Long> academyChildIdList2 = new ArrayList<>();
//            academyChildIdList1.add(academyChild3.getId());
//
//            List<RouteStationAcademyChildRequest> requestList = new ArrayList<>();
//            RouteStationAcademyChildRequest request1 = new RouteStationAcademyChildRequest(stationId1, academyChildIdList1);
//            RouteStationAcademyChildRequest request2 = new RouteStationAcademyChildRequest(stationId2, academyChildIdList2);
//            requestList.add(request1);
//            requestList.add(request2);
//
//            routeFacade.addAcademyChildToRouteStation(academyId, routeId, requestList);
//        }
//    }
//
//    @Nested
//    @DisplayName("노선 목록 조회 테스트")
//    class getAllRoute {
//
//        @Test
//        @DisplayName("성공")
//        void whenSuccess() throws Exception {
//            long academyId = academy.getId();
//            routeFacade.getAllRoute(academyId);
//        }
//    }
//
//    @Nested
//    @DisplayName("노선 상세 조회 테스트")
//    class getRoute {
//
//        @Test
//        @DisplayName("성공")
//        void whenSuccess() throws Exception {
//            long academyId = academy.getId();
//
//            Route route = Route.of(academy, "등원A", "board");
//            long routeId = routeService.save(route);
//
//            routeFacade.getRoute(academyId, routeId);
//        }
//    }
//
//}
