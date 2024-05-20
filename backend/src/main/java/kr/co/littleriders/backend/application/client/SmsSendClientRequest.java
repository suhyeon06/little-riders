package kr.co.littleriders.backend.application.client;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.driver.entity.Driver;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import lombok.Getter;
import lombok.Setter;

@Getter
public class SmsSendClientRequest {

    private final String to;
    @Setter
    private String from;
    private final String text;
    private final String type = "LMS";
    private final String subject = "리틀라이더즈 알림";

    private SmsSendClientRequest(String to, String text) {
        this.to = to;
        this.text = text;
    }

    public static SmsSendClientRequest of(AcademyChild academyChild, String text) {
        return new SmsSendClientRequest(academyChild.getPhoneNumber(), text);
    }

    public static SmsSendClientRequest toBoardMessage(String uuid, AcademyChild academyChild,Teacher teacher, Driver driver, Shuttle shuttle) {
        Academy academy = academyChild.getAcademy();
        String childName = academyChild.getName();
        String academyPhoneNumber = academy.getPhoneNumber();
        String driverPhoneNumber = driver.getPhoneNumber();
        String teacherPhoneNumber = teacher.getPhoneNumber();

        String text = "[승차 알림]\n" +
                String.format("띵동~! %s 어린이가 승차했습니다!\n",childName)+
                "오늘도 안전하게 목적지로 운행할게요!\n" +
                academyAndDriveAndTeacherTemplate(academyPhoneNumber,driverPhoneNumber,teacherPhoneNumber)+
                uuidTemplate(uuid);


        return SmsSendClientRequest.of(academyChild,text);
    }

    public static SmsSendClientRequest toDropMessage(String uuid, AcademyChild academyChild, Teacher teacher, Driver driver, Shuttle shuttle) {
        Academy academy = academyChild.getAcademy();
        String childName = academyChild.getName();
        String academyPhoneNumber = academy.getPhoneNumber();
        String driverPhoneNumber = driver.getPhoneNumber();
        String teacherPhoneNumber = teacher.getPhoneNumber();

        String text = "[하차 알림]\n" +
                String.format("띵동~! %s 어린이가 하차했습니다!\n",childName)+
                "오늘도 안전하게 운행했어요!\n" +
                academyAndDriveAndTeacherTemplate(academyPhoneNumber,driverPhoneNumber,teacherPhoneNumber)+
                uuidTemplate(uuid);
        return SmsSendClientRequest.of(academyChild,text);

    }

    public static SmsSendClientRequest toStartDriveMessage(String uuid, AcademyChild academyChild, Teacher teacher, Driver driver, Shuttle shuttle) {
        Academy academy = academyChild.getAcademy();
        String academyName = academy.getName();
        String academyPhoneNumber = academy.getPhoneNumber();
        String driverPhoneNumber = driver.getPhoneNumber();
        String teacherPhoneNumber = teacher.getPhoneNumber();
        String licenseNumber = shuttle.getLicenseNumber();
        String text = shuttleStartTemplate(uuid,academyName,academyPhoneNumber,driverPhoneNumber,teacherPhoneNumber,licenseNumber);
        return SmsSendClientRequest.of(academyChild,text);
    }

    private static String shuttleStartTemplate(
            String uuid,
            String academyName,
            String academyPhoneNumber,
            String driverPhoneNumber,
            String teacherPhoneNumber,
            String licenseNumber
    ) {
        return "[출발 알림]\n"+
                String.format("%s 셔틀 운행이 시작됐습니다!\n",academyName)+
                "정해진 정류장에서 잘 승차할 수 있도록 지도해주세요!\n"+
                academyAndDriveAndTeacherTemplate(academyPhoneNumber,driverPhoneNumber,teacherPhoneNumber)+
                String.format("차량 번호 : %s\n",licenseNumber)+
                uuidTemplate(uuid);


    }

    private static String academyAndDriveAndTeacherTemplate(
            String academyPhoneNumber,
            String driverPhoneNumber,
            String teacherPhoneNumber
            ){

        return String.format("학원 번호 : %s\n", academyPhoneNumber) +
                String.format("기사님 번호 : %s\n", driverPhoneNumber) +
                String.format("선생님 번호 : %s\n", teacherPhoneNumber);
    }
    private static String uuidTemplate(String uuid){
        return String.format("더 많은 정보를 보려면 아래 링크를 눌러주세요!\nhttps://littleriders.co.kr/parent-view/%s",uuid);
    }

}
