package kr.co.littleriders.backend.application.client;

import kr.co.littleriders.backend.common.fixture.*;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChildStatus;
import kr.co.littleriders.backend.domain.driver.entity.Driver;
import kr.co.littleriders.backend.domain.driver.entity.DriverStatus;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleStatus;
import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import kr.co.littleriders.backend.domain.teacher.entity.TeacherStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@Slf4j
class SmsFetchAPITest {
    @Autowired
    SmsFetchAPI smsFetchAPI;


    @Test
    @DisplayName("단체 메시지 발송")
    void tests(){
//        String result = smsFetchAPI.sendLMS();
//        log.info("result = {}",result);

        Academy academy = AcademyFixture.BOXING.toAcademy();
        Driver driver = DriverFixture.YOON.toDriver(academy, DriverStatus.WORK);
        Teacher teacher = TeacherFixture.NAM.toTeacher(academy, TeacherStatus.WORK);
        Shuttle shuttle = ShuttleFixture.HO_11.toShuttle(academy, ShuttleStatus.USE);


        AcademyChildFixture academyChildFixture1 = AcademyChildFixture.KIM;
        AcademyChildFixture academyChildFixture2 = AcademyChildFixture.KANG;
        AcademyChildFixture academyChildFixture3 = AcademyChildFixture.CHOI;
        AcademyChildFixture academyChildFixture4 = AcademyChildFixture.PARK;

        List<AcademyChild> academyChildList = new ArrayList<>();
        academyChildFixture1.setPhoneNumber("           ");
        academyChildFixture2.setPhoneNumber("           ");
        academyChildFixture3.setPhoneNumber("           ");
        academyChildFixture4.setPhoneNumber("           ");
        academyChildList.add(academyChildFixture1.toAcademyChild(academy,null,AcademyChildStatus.ATTENDING));
        academyChildList.add(academyChildFixture2.toAcademyChild(academy,null,AcademyChildStatus.ATTENDING));
        academyChildList.add(academyChildFixture3.toAcademyChild(academy,null,AcademyChildStatus.ATTENDING));
        academyChildList.add(academyChildFixture4.toAcademyChild(academy,null,AcademyChildStatus.ATTENDING));

        List<SmsSendClientRequest> smsSendClientRequestList = new ArrayList<>();
        for(AcademyChild academyChild : academyChildList){
            String uuid = UUID.randomUUID().toString();
            smsSendClientRequestList.add(SmsSendClientRequest.toStartDriveMessage(
                uuid, academyChild,teacher,driver,shuttle
            ));
        }

        String result = smsFetchAPI.sendLMS(smsSendClientRequestList);
        System.out.println(result);
//        String result = smsFetchAPI.sendLMS(smsSendClientRequest);
//        System.out.println(result);
//        System.out.println(academyChild.getPhoneNumber());
//        SmsSendClientRequest smsSendClientRequest = SmsSendClientRequest.of()

    }

}