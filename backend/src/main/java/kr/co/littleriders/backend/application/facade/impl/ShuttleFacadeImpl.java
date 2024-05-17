package kr.co.littleriders.backend.application.facade.impl;

import kr.co.littleriders.backend.application.client.SmsFetchAPI;
import kr.co.littleriders.backend.application.client.SmsSendClientRequest;
import kr.co.littleriders.backend.application.dto.request.ShuttleChildBoardRequest;
import kr.co.littleriders.backend.application.dto.request.ShuttleChildDropRequest;
import kr.co.littleriders.backend.application.dto.request.ShuttleLocationRequest;
import kr.co.littleriders.backend.application.dto.request.ShuttleStartRequest;
import kr.co.littleriders.backend.application.dto.response.*;
import kr.co.littleriders.backend.application.facade.ShuttleFacade;
import kr.co.littleriders.backend.application.facade.SseFacade;
import kr.co.littleriders.backend.domain.academy.AcademyChildService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.beacon.BeaconServcie;
import kr.co.littleriders.backend.domain.beacon.entity.Beacon;
import kr.co.littleriders.backend.domain.driver.DriverService;
import kr.co.littleriders.backend.domain.driver.entity.Driver;
import kr.co.littleriders.backend.domain.driver.error.code.DriverErrorCode;
import kr.co.littleriders.backend.domain.driver.error.exception.DriverException;
import kr.co.littleriders.backend.domain.history.ShuttleBoardDropHistoryService;
import kr.co.littleriders.backend.domain.history.ShuttleDriveHistoryService;
import kr.co.littleriders.backend.domain.history.entity.ShuttleBoardDropHistory;
import kr.co.littleriders.backend.domain.history.entity.ShuttleDriveHistory;
import kr.co.littleriders.backend.domain.route.RouteService;
import kr.co.littleriders.backend.domain.route.entity.Route;
import kr.co.littleriders.backend.domain.route.error.code.RouteErrorCode;
import kr.co.littleriders.backend.domain.route.error.exception.RouteException;
import kr.co.littleriders.backend.domain.routeinfo.entity.ChildBoardDropInfo;
import kr.co.littleriders.backend.domain.shuttle.DriveUniqueKeyService;
import kr.co.littleriders.backend.domain.shuttle.ShuttleDriveService;
import kr.co.littleriders.backend.domain.shuttle.ShuttleLocationService;
import kr.co.littleriders.backend.domain.shuttle.ShuttleService;
import kr.co.littleriders.backend.domain.shuttle.entity.DriveUniqueKey;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleDrive;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleLocation;
import kr.co.littleriders.backend.domain.shuttle.*;
import kr.co.littleriders.backend.domain.shuttle.entity.*;
import kr.co.littleriders.backend.domain.shuttle.error.code.ShuttleBoardErrorCode;
import kr.co.littleriders.backend.domain.shuttle.error.code.ShuttleDriveErrorCode;
import kr.co.littleriders.backend.domain.shuttle.error.code.ShuttleErrorCode;
import kr.co.littleriders.backend.domain.shuttle.error.exception.ShuttleBoardException;
import kr.co.littleriders.backend.domain.shuttle.error.exception.ShuttleDriveException;
import kr.co.littleriders.backend.domain.shuttle.error.exception.ShuttleException;
import kr.co.littleriders.backend.domain.teacher.TeacherService;
import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import kr.co.littleriders.backend.domain.teacher.error.code.TeacherErrorCode;
import kr.co.littleriders.backend.domain.teacher.error.exception.TeacherException;
import kr.co.littleriders.backend.global.auth.dto.AuthTerminal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ShuttleFacadeImpl implements ShuttleFacade {

    private final ShuttleService shuttleService;
    private final RouteService routeService;
    private final DriverService driverService;
    private final TeacherService teacherService;
    private final AcademyChildService academyChildService;

    private final ShuttleLocationService shuttleLocationService;
    private final ShuttleDriveService shuttleDriveService;
    private final ShuttleDropService shuttleDropService;
    private final DriveUniqueKeyService driveUniqueKeyService;
    private final ShuttleBoardService shuttleBoardService;
    private final SseFacade sseFacade;
    private final SmsFetchAPI smsFetchAPI;
    private final BeaconServcie beaconServcie;
    private final ShuttleBoardDropHistoryService shuttleBoardDropHistoryService;


    private final ShuttleDriveHistoryService shuttleDriveHistoryService;

    @Override
    public List<RouteResponse> getRouteList(AuthTerminal authTerminal) {
        long shuttleId = authTerminal.getShuttleId();
        Shuttle shuttle = shuttleService.findById(shuttleId);
        Academy academy = shuttle.getAcademy();

        List<Route> routeList = routeService.findAllByAcademy(academy);

        return routeList.stream()
                .map(RouteResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<RouteDetailResponse> getRouteListWithStation(AuthTerminal authTerminal) {
        long shuttleId = authTerminal.getShuttleId();
        Shuttle shuttle = shuttleService.findById(shuttleId);
        Academy academy = shuttle.getAcademy();

        List<Route> routeList = routeService.findAllByAcademy(academy);

        return routeList.stream()
                .map(RouteDetailResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public RouteDetailResponse getRoute(AuthTerminal authTerminal, long routeId) {
        Route route = routeService.findById(routeId);

        Shuttle shuttle = shuttleService.findById(authTerminal.getShuttleId());
        long academyId = shuttle.getAcademy().getId();

        if(!Objects.equals(academyId, route.getAcademy().getId())) {
            throw RouteException.from(RouteErrorCode.FORBIDDEN);
        }

        return RouteDetailResponse.from(route);
    }

    @Override
    @Transactional
    public void startDrive(AuthTerminal authTerminal, ShuttleStartRequest startRequest) {



        long shuttleId = authTerminal.getShuttleId();

        //셔틀이 운행중이면 에러
        if(shuttleDriveService.existsByShuttleId(shuttleId)){
            throw ShuttleDriveException.from(ShuttleDriveErrorCode.ALREADY_DRIVE);
        }

        if (routeService.notExistsById(startRequest.getRouteId())) {
            throw RouteException.from(RouteErrorCode.NOT_FOUND);
        }

        if (driverService.notExistsById(startRequest.getDriverId())) {
            throw DriverException.from(DriverErrorCode.NOT_FOUND);
        }

        if (teacherService.notExistsById(startRequest.getTeacherId())) {
            throw TeacherException.from(TeacherErrorCode.NOT_FOUND);
        }

        Shuttle shuttle = shuttleService.findById(shuttleId);
        Teacher teacher = teacherService.findById(startRequest.getTeacherId());
        Driver driver = driverService.findById(startRequest.getDriverId());
        Route route = routeService.findById(startRequest.getRouteId());

        if (!Objects.equals(shuttle.getAcademy().getId(), route.getAcademy().getId())) {
            throw ShuttleException.from(ShuttleErrorCode.FORBIDDEN);
        }

        // 셔틀 정보 저장
        ShuttleDrive shuttleDrive = startRequest.toShuttleDrive(shuttleId);
        shuttleDriveService.save(shuttleDrive);

        // 탑승 원생 리스트
        List<AcademyChild> academyChildList = route.getRouteStationList().stream()
                .flatMap(station -> station.getChildBoardInfoList().stream())
                .map(ChildBoardDropInfo::getAcademyChild)
                .toList();

        List<SmsSendClientRequest> smsSendClientRequestList = new ArrayList<>();

        academyChildList.forEach(academyChild -> {
            String uuid = UUID.randomUUID().toString();
            long academyChildId = academyChild.getId();
            DriveUniqueKey driveUniqueKey = DriveUniqueKey.of(uuid, shuttleId, academyChildId);
            driveUniqueKeyService.save(driveUniqueKey);

            smsSendClientRequestList.add(SmsSendClientRequest.toStartDriveMessage(
                    uuid, academyChild, teacher, driver, shuttle
            ));
        });

        sseFacade.broadcastStartDriveByAcademyId(shuttle.getAcademy().getId(),shuttleDrive); //운행시작 알림
        log.info("size = {}",smsSendClientRequestList.size());
        // 운행 시작 sms 발송
//        smsFetchAPI.sendLMS(smsSendClientRequestList);
    }

    @Override
    @Transactional
    public void endDrive(long shuttleId) {



        ShuttleDrive shuttleDrive = shuttleDriveService.findByShuttleId(shuttleId);
        long driverId = shuttleDrive.getDriverId();
        long teacherId = shuttleDrive.getTeacherId();
        long routeId = shuttleDrive.getRouteId();

        Route route = routeService.findById(routeId);
        Driver driver = driverService.findById(driverId);
        Teacher teacher = teacherService.findById(teacherId);
        Shuttle shuttle  = shuttleService.findById(shuttleId);
        LocalDateTime start = shuttleDrive.getTime();
        LocalDateTime end = LocalDateTime.now();


        //TODO - 김도현 - 어린이 저장 및 정상 종료 여부 확인하기



        //학부모 접근권한 모두 가져오기
        List<DriveUniqueKey> driveUniqueKeyList = driveUniqueKeyService.findByShuttleId(shuttleId);

        //승차정보 모두 가져오기
        List<ShuttleBoard> shuttleBoardList = shuttleBoardService.findByShuttleId(shuttleId);
        //하차정보 모두 가져오기
        List<ShuttleDrop> shuttleDropList = shuttleDropService.findByShuttleId(shuttleId);

        //지나왔던길 모두 가져오기
        List<ShuttleLocation> shuttleLocationList = shuttleLocationService.findByShuttleId(shuttleId);
        shuttleLocationList.sort(Comparator.comparing(ShuttleLocation::getTime));

        //TODO - 김도현 - 승차 하차정보 모두 저장해야함.

        List<ShuttleDriveHistory.BoardDropInfoInShuttleDriveHistory> boardList = new ArrayList<>();
        List<ShuttleDriveHistory.BoardDropInfoInShuttleDriveHistory> dropList = new ArrayList<>();
        for(ShuttleBoard shuttleBoard : shuttleBoardList){
            AcademyChild academyChild = academyChildService.findById(shuttleBoard.getAcademyChildId());
            boardList.add(ShuttleDriveHistory.BoardDropInfoInShuttleDriveHistory.of(academyChild,
                    shuttleBoard.getLatitude(),
                    shuttleBoard.getLongitude(),
                    shuttleBoard.getTime()));
        }

        for(ShuttleDrop shuttleDrop : shuttleDropList){
            AcademyChild academyChild = academyChildService.findById(shuttleDrop.getAcademyChildId());
            dropList.add(ShuttleDriveHistory.BoardDropInfoInShuttleDriveHistory.of(academyChild,
                    shuttleDrop.getLatitude(),
                    shuttleDrop.getLongitude(),
                    shuttleDrop.getTime()));
        }

        ShuttleDriveHistory shuttleDriveHistory = ShuttleDriveHistory.of(route.getName(),start,end,shuttle,driver,teacher,shuttleLocationList,boardList,dropList);
        shuttleDriveHistoryService.save(shuttleDriveHistory);

        shuttleDriveService.delete(shuttleDrive);
        driveUniqueKeyService.deleteAllByShuttleId(shuttleId); //비정상 종료인경우 처리임
        shuttleBoardService.deleteAllByShuttleId(shuttleId);    //승차정보 모두 삭제
        shuttleDropService.deleteAllByShuttleId(shuttleId);     //하차정보 모두 삭제
        shuttleLocationService.deleteAllByShuttleId(shuttleId); //버스 위치 모두 삭제
        //운행종료 알림 발송
        sseFacade.broadcastEndDriveByShuttleId(shuttleId);

    }

    @Override
    @Transactional
    public ShuttleChildBoardResponse recordChildBoard(AuthTerminal authTerminal, ShuttleChildBoardRequest boardRequest) {



        // TODO: 졸업을 한 아이에 대한 valid check 추가

        long shuttleId = authTerminal.getShuttleId();
        Shuttle shuttle = shuttleService.findById(shuttleId);
        Academy academy = shuttle.getAcademy();

        String beaconUUID = boardRequest.getBeaconUUID();
        Beacon beacon = beaconServcie.findByUuid(beaconUUID);
        long academyChildId = beacon.getAcademyChild().getId();
        DriveUniqueKey driveUniqueKey = driveUniqueKeyService.findByAcademyChildId(academyChildId); //DriveUniqueKey 를 못 찾은 경우 오류터짐

        ShuttleBoard shuttleBoard = boardRequest.toShuttleBoard(driveUniqueKey, academy.getId());

        //TODO - 김도현 - 승차기록이 있다면 재승차임. 또는  그럴경우 exception 터트려야함
        if(shuttleBoardService.existsByAcademyChildId(academyChildId)){
            throw ShuttleBoardException.from(ShuttleBoardErrorCode.ALREADY_BOARD);
        }

        // redis에 승차 기록 저장
        shuttleBoardService.save(shuttleBoard);

        ShuttleDrive shuttleDrive = shuttleDriveService.findByShuttleId(shuttleId);
        AcademyChild academyChild = academyChildService.findById(driveUniqueKey.getAcademyChildId());
        Teacher teacher = teacherService.findById(shuttleDrive.getTeacherId());
        Driver driver = driverService.findById(shuttleDrive.getDriverId());


        //구독하고있는 학원과 viewer 에 승차정보 전송
        sseFacade.broadcastBoardByAcademyIdAndViewerId(shuttleId,academy.getId(),driveUniqueKey.getUuid(),academyChild, boardRequest.getLatitude(), boardRequest.getLongitude());


        log.info("BOARD = {}",driveUniqueKey.getUuid());
        // 승차 sms 전송
        SmsSendClientRequest smsSendClientRequest = SmsSendClientRequest.toBoardMessage(driveUniqueKey.getUuid(), academyChild, teacher, driver, shuttle);
//        smsFetchAPI.sendLMS(smsSendClientRequest);

        return ShuttleChildBoardResponse.from(academyChild);
    }

    @Override
    @Transactional
    public ShuttleChildDropResponse recordChildDrop(AuthTerminal authTerminal, ShuttleChildDropRequest dropRequest) {

        long shuttleId = authTerminal.getShuttleId();
        Shuttle shuttle = shuttleService.findById(shuttleId);
        Academy academy = shuttle.getAcademy();

        String beaconUUID = dropRequest.getBeaconUUID();
        Beacon beacon = beaconServcie.findByUuid(beaconUUID);
        long academyChildId = beacon.getAcademyChild().getId();
        DriveUniqueKey driveUniqueKey = driveUniqueKeyService.findByAcademyChildId(academyChildId); //운행정보가 없는데 하차 시도시에도 예외
        String driveUniqueKeyUuid = driveUniqueKey.getUuid();

        // 승차 기록이 없으면 예외 발생
        if(shuttleBoardService.notExistsByAcademyChildId(academyChildId)) {
            throw ShuttleBoardException.from(ShuttleBoardErrorCode.NOT_FOUND);
        }

        // redis에 하차 기록 저장
        ShuttleDrop shuttleDrop = dropRequest.toShuttleDrop(driveUniqueKey, academy.getId());
        shuttleDropService.save(shuttleDrop);

        // redis에서 해당 driveUniqueKey 삭제
        driveUniqueKeyService.delete(driveUniqueKey);

        ShuttleDrive shuttleDrive = shuttleDriveService.findByShuttleId(shuttleId);
        AcademyChild academyChild = academyChildService.findById(driveUniqueKey.getAcademyChildId());
        Teacher teacher = teacherService.findById(shuttleDrive.getTeacherId());
        Driver driver = driverService.findById(shuttleDrive.getDriverId());
        ShuttleBoard shuttleBoard = shuttleBoardService.findByAcademyChildId(academyChildId);

        List<ShuttleLocation> shuttleLocationList = shuttleLocationService.findByShuttleId(shuttleId);
        shuttleLocationList.sort(Comparator.comparing(ShuttleLocation::getTime));

        // mongoDB에 저장
        ShuttleBoardDropHistory shuttleBoardDropHistory = ShuttleBoardDropHistory.of(
                driveUniqueKeyUuid,
                shuttle,
                driver,
                teacher,
                shuttleBoard,
                shuttleDrop,
                shuttleLocationList,
                academyChild
        );
        shuttleBoardDropHistoryService.save(shuttleBoardDropHistory);

        //구독하고있는 학원과 viewer 에 하차정보 전송
        sseFacade.broadcastDropByAcademyIdAndViewerId(shuttleId,academy.getId(),driveUniqueKeyUuid,academyChild, dropRequest.getLatitude(), dropRequest.getLongitude());



        log.info("DROP = {}",driveUniqueKeyUuid);
        // 하차 sms 전송
        SmsSendClientRequest smsSendClientRequest = SmsSendClientRequest.toDropMessage(driveUniqueKeyUuid, academyChild, teacher, driver, shuttle);
//        smsFetchAPI.sendLMS(smsSendClientRequest);

        return ShuttleChildDropResponse.from(academyChild);
    }



    @Override
    @Transactional
    public void uploadLocation(AuthTerminal authTerminal, ShuttleLocationRequest locationRequest) {


//TODO - 김도현 - 운행중이 아니면 exception
//        if(shuttleDriveService.notExistsByShuttleId(authTerminal.getShuttleId())){
//            throw ShuttleDriveException.from(ShuttleDriveErrorCode.NOT_FOUND);
//        }
        long shuttleId = authTerminal.getShuttleId();

        ShuttleLocation location = locationRequest.toShuttleLocation(shuttleId);
        shuttleLocationService.save(location);

        sseFacade.broadcastShuttleLocationByShuttleId(shuttleId,locationRequest);

    }

    @Override
    public DriverInfoResponse getDriverInfoByCardNumber(AuthTerminal authTerminal, String cardNumber) {

        //TODO - 김도현 - Permission check

        Driver driver = driverService.findByCardNumber(cardNumber);
//        Shuttle shuttle = shuttleService.findById(authTerminal.getShuttleId());
        //permissionHelper.check(shuttle,driver);

        //TODO - 김도현 - if not Work then throw exception

        return DriverInfoResponse.from(driver);


    }

    @Override
    public TeacherInfoResponse getTeacherInfoByCardNumber(AuthTerminal authTerminal, String cardNumber) {

        //TODO - 김도현 - Permission check
        Teacher teacher = teacherService.findByCardNumber(cardNumber);
        //Shuttle shuttle = shuttleService.findById(authTerminal.getShuttleId());
        //permissionHelper.check(shuttle,teacher);


        return TeacherInfoResponse.from(teacher);

        //TODO - 김도현 - if not Work then throw exception

    }


}
