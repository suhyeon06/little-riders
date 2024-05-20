package kr.co.littleriders.backend;

import kr.co.littleriders.backend.common.fixture.*;
import kr.co.littleriders.backend.domain.academy.AcademyChildService;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChildStatus;
import kr.co.littleriders.backend.domain.beacon.BeaconServcie;
import kr.co.littleriders.backend.domain.beacon.entity.Beacon;
import kr.co.littleriders.backend.domain.driver.DriverService;
import kr.co.littleriders.backend.domain.driver.entity.Driver;
import kr.co.littleriders.backend.domain.driver.entity.DriverStatus;
import kr.co.littleriders.backend.domain.route.RouteService;
import kr.co.littleriders.backend.domain.route.entity.Route;
import kr.co.littleriders.backend.domain.shuttle.ShuttleService;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleStatus;
import kr.co.littleriders.backend.domain.station.StationService;
import kr.co.littleriders.backend.domain.station.entity.Station;
import kr.co.littleriders.backend.domain.teacher.TeacherService;
import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import kr.co.littleriders.backend.domain.teacher.entity.TeacherStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    AcademyService academyService;

    @Autowired
    ShuttleService shuttleService;

    @Autowired
    StationService stationService;

    @Autowired
    RouteService routeService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    DriverService driverService;

    @Autowired
    BeaconServcie beaconServcie;

    @Autowired
    AcademyChildService academyChildService;


    @Test
    void contextLoads() {
        Academy academy = AcademyFixture.BOXING.toAcademy();
        Academy academy1 = AcademyFixture.BASEBALL.toAcademy();
        Academy academy2 = AcademyFixture.SOCCER.toAcademy();
        Academy academy3 = AcademyFixture.COMPUTER.toAcademy();
        academyService.save(academy);
        academyService.save(academy1);
        academyService.save(academy2);
        academyService.save(academy3);


        List<Academy> academyList = new ArrayList<>();
        academyList.add(academy);
        academyList.add(academy1);
        academyList.add(academy2);
        academyList.add(academy3);


        List<ShuttleFixture> shuttleFixtureList = new ArrayList<>();
        shuttleFixtureList.add(ShuttleFixture.HO_01);
        shuttleFixtureList.add(ShuttleFixture.HO_02);
        shuttleFixtureList.add(ShuttleFixture.HO_03);
        shuttleFixtureList.add(ShuttleFixture.HO_04);
        shuttleFixtureList.add(ShuttleFixture.HO_05);
        shuttleFixtureList.add(ShuttleFixture.HO_06);
        shuttleFixtureList.add(ShuttleFixture.HO_07);
        shuttleFixtureList.add(ShuttleFixture.HO_08);
        shuttleFixtureList.add(ShuttleFixture.HO_09);
        shuttleFixtureList.add(ShuttleFixture.HO_10);

        for (Academy ac : academyList) {
            for (ShuttleFixture shuttleFixture : shuttleFixtureList) {
                Shuttle shuttle = shuttleFixture.toShuttle(ac, ShuttleStatus.USE);
                shuttleService.save(shuttle);
            }
        }

        List<RouteFixture> routeFixtureList = new ArrayList<>();
        routeFixtureList.add(RouteFixture.A);
        routeFixtureList.add(RouteFixture.B);
        routeFixtureList.add(RouteFixture.C);
        routeFixtureList.add(RouteFixture.D);
        routeFixtureList.add(RouteFixture.E);
        routeFixtureList.add(RouteFixture.F);
        routeFixtureList.add(RouteFixture.G);
        routeFixtureList.add(RouteFixture.H);
        routeFixtureList.add(RouteFixture.I);
        routeFixtureList.add(RouteFixture.J);
        routeFixtureList.add(RouteFixture.K);
        routeFixtureList.add(RouteFixture.L);
        routeFixtureList.add(RouteFixture.M);
        routeFixtureList.add(RouteFixture.N);
        routeFixtureList.add(RouteFixture.O);
        routeFixtureList.add(RouteFixture.P);
        routeFixtureList.add(RouteFixture.Q);
        routeFixtureList.add(RouteFixture.R);
        routeFixtureList.add(RouteFixture.S);

        for (Academy ac : academyList) {
            for (RouteFixture routeFixture : routeFixtureList) {
                Route route = routeFixture.toRoute(ac, "등교");
                routeService.save(route);
            }
        }

        List<TeacherFixture> teacherFixtureList = new ArrayList<>();
        teacherFixtureList.add(TeacherFixture.CHOO);
        teacherFixtureList.add(TeacherFixture.PARK);
        teacherFixtureList.add(TeacherFixture.SHIN);
        teacherFixtureList.add(TeacherFixture.CHOI);
        teacherFixtureList.add(TeacherFixture.KIM);
        teacherFixtureList.add(TeacherFixture.HONG);
        teacherFixtureList.add(TeacherFixture.CHA);
        teacherFixtureList.add(TeacherFixture.NAM);
        teacherFixtureList.add(TeacherFixture.HA);
        teacherFixtureList.add(TeacherFixture.NAM_GOONG);


        for (Academy ac : academyList) {
            for (TeacherFixture teacherFixture : teacherFixtureList) {
                Teacher teacher = teacherFixture.toTeacher(ac, TeacherStatus.WORK);
                teacherService.save(teacher);
            }
        }

        List<DriverFixture> driverFixtureList = new ArrayList<>();


        driverFixtureList.add(DriverFixture.CHOO);
        driverFixtureList.add(DriverFixture.CHUN);
        driverFixtureList.add(DriverFixture.GANG);
        driverFixtureList.add(DriverFixture.MIN);
        driverFixtureList.add(DriverFixture.UM);
        driverFixtureList.add(DriverFixture.YOON);
        driverFixtureList.add(DriverFixture.MOON);
        driverFixtureList.add(DriverFixture.HWANG_BO);
        driverFixtureList.add(DriverFixture.LIM);
        driverFixtureList.add(DriverFixture.CHE_GAL);

        for (Academy ac : academyList) {
            for (DriverFixture driverFixture : driverFixtureList) {
                Driver driver = driverFixture.toDriver(ac, DriverStatus.WORK);
                driverService.save(driver);
            }
        }


        List<StationFixture> stationFixtureList = new ArrayList<>();

        stationFixtureList.add(StationFixture.GANG_NAM);
        stationFixtureList.add(StationFixture.UN_JU);
        stationFixtureList.add(StationFixture.YEOK_SAM);
        stationFixtureList.add(StationFixture.SUN_REONG);
        stationFixtureList.add(StationFixture.SHIN_NON_HYUN);
        stationFixtureList.add(StationFixture.NON_HYUN);
        stationFixtureList.add(StationFixture.HAK_DONG);
        stationFixtureList.add(StationFixture.BAN_PO);
        stationFixtureList.add(StationFixture.GYO_DAE_1_EXIT);
        stationFixtureList.add(StationFixture.GYO_DAE_9_EXIT);

        for (Academy ac : academyList) {
            for (StationFixture stationFixture : stationFixtureList) {
                Station station = stationFixture.toStation(ac);
                stationService.save(station);
            }
        }

        List<BeaconFixture> beaconFixtureList = new ArrayList<>();
        beaconFixtureList.add(BeaconFixture.A);
        beaconFixtureList.add(BeaconFixture.B);
        beaconFixtureList.add(BeaconFixture.C);
        beaconFixtureList.add(BeaconFixture.D);
        beaconFixtureList.add(BeaconFixture.E);
        beaconFixtureList.add(BeaconFixture.F);
        beaconFixtureList.add(BeaconFixture.G);
        beaconFixtureList.add(BeaconFixture.H);
        beaconFixtureList.add(BeaconFixture.I);
        beaconFixtureList.add(BeaconFixture.J);
        beaconFixtureList.add(BeaconFixture.K);
        beaconFixtureList.add(BeaconFixture.L);
        beaconFixtureList.add(BeaconFixture.M);
        beaconFixtureList.add(BeaconFixture.N);
        beaconFixtureList.add(BeaconFixture.O);
        beaconFixtureList.add(BeaconFixture.P);
        beaconFixtureList.add(BeaconFixture.Q);
        beaconFixtureList.add(BeaconFixture.R);
        beaconFixtureList.add(BeaconFixture.S);
        beaconFixtureList.add(BeaconFixture.T);
        beaconFixtureList.add(BeaconFixture.U);
        beaconFixtureList.add(BeaconFixture.V);
        beaconFixtureList.add(BeaconFixture.W);
        beaconFixtureList.add(BeaconFixture.X);
        beaconFixtureList.add(BeaconFixture.Y);
        beaconFixtureList.add(BeaconFixture.Z);


        List<AcademyChildFixture> academyChildFixtureList = new ArrayList<>();
        academyChildFixtureList.add(AcademyChildFixture.KIM);
        academyChildFixtureList.add(AcademyChildFixture.KANG);
        academyChildFixtureList.add(AcademyChildFixture.CHOI);
        academyChildFixtureList.add(AcademyChildFixture.PARK);

        for (int i  = 0 ; i <  4; i++){
            Beacon beacon = beaconFixtureList.get(i).toBeacon(academy);

            beaconServcie.save(beacon);
            AcademyChild academyChild = academyChildFixtureList.get(i).toAcademyChild(academy,beacon, AcademyChildStatus.ATTENDING);
            academyChildService.save(academyChild);
        }
        for (int i  = 4 ; i <  8; i++){
            Beacon beacon = beaconFixtureList.get(i).toBeacon(academy1);
            beaconServcie.save(beacon);
            AcademyChild academyChild = academyChildFixtureList.get(i-4).toAcademyChild(academy1,beacon, AcademyChildStatus.ATTENDING);
            academyChildService.save(academyChild);
        }
        for (int i  = 8 ; i <  12; i++){
            Beacon beacon = beaconFixtureList.get(i).toBeacon(academy2);
            beaconServcie.save(beacon);
            AcademyChild academyChild = academyChildFixtureList.get(i-8).toAcademyChild(academy2,beacon, AcademyChildStatus.ATTENDING);
            academyChildService.save(academyChild);
        }
        for (int i  = 12 ; i <  16; i++){
            Beacon beacon = beaconFixtureList.get(i).toBeacon(academy3);
            beaconServcie.save(beacon);
            AcademyChild academyChild = academyChildFixtureList.get(i-12).toAcademyChild(academy3,beacon, AcademyChildStatus.ATTENDING);
            academyChildService.save(academyChild);
        }






    }

}
