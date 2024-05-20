package kr.co.littleriders.backend.domain.history.entity;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.driver.entity.Driver;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleLocation;
import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import kr.co.littleriders.backend.global.entity.Gender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("shuttle_drive_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ShuttleDriveHistory {

    @Id
    private String documentId;

    private ShuttleInShuttleDriveHistory shuttle;
    private DriverInShuttleDriveHistory driver;
    private TeacherInShuttleDriveHistory teacher;
    private List<LocationInShuttleDriveHistory> locationList;

    private List<BoardDropInfoInShuttleDriveHistory> boardList;
    private List<BoardDropInfoInShuttleDriveHistory> dropList;

    private String routeName;
    private LocalDateTime start;
    private LocalDateTime end;

    private ShuttleDriveHistory(
            String routeName,
            LocalDateTime start,
            LocalDateTime end,
            ShuttleInShuttleDriveHistory shuttleDriveHistory,
                                DriverInShuttleDriveHistory driverInShuttleDriveHistory,
                                TeacherInShuttleDriveHistory teacherInShuttleDriveHistory,
                                List<LocationInShuttleDriveHistory> locationList,
                                List<BoardDropInfoInShuttleDriveHistory> boardList,
                                List<BoardDropInfoInShuttleDriveHistory> dropList
                                ) {
        this.shuttle = shuttleDriveHistory;
        this.driver = driverInShuttleDriveHistory;
        this.teacher = teacherInShuttleDriveHistory;
        this.locationList = locationList;
        this.routeName = routeName;
        this.start = start;
        this.end = end;
        this.boardList = boardList;
        this.dropList = dropList;
    }

    public static ShuttleDriveHistory of(String routeName, LocalDateTime start, LocalDateTime end, Shuttle shuttle, Driver driver, Teacher teacher, List<ShuttleLocation> shuttleLocationList, List<BoardDropInfoInShuttleDriveHistory> boardList, List<BoardDropInfoInShuttleDriveHistory> dropList) {

        ShuttleInShuttleDriveHistory shuttleInShuttleDriveHistory = ShuttleInShuttleDriveHistory.from(shuttle);
        DriverInShuttleDriveHistory driverInShuttleDriveHistory = DriverInShuttleDriveHistory.from(driver);
        TeacherInShuttleDriveHistory teacherInShuttleDriveHistory = TeacherInShuttleDriveHistory.from(teacher);
        List<LocationInShuttleDriveHistory> locationInShuttleDriveHistoryList = shuttleLocationList.stream().map(LocationInShuttleDriveHistory::from).toList();
        return new ShuttleDriveHistory(routeName, start, end, shuttleInShuttleDriveHistory, driverInShuttleDriveHistory, teacherInShuttleDriveHistory, locationInShuttleDriveHistoryList, boardList, dropList);
    }


    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ShuttleInShuttleDriveHistory {
        private long id;
        private String name;
        private String type;
        private String licenseNumber;

        private static ShuttleInShuttleDriveHistory from(Shuttle shuttle) {
            long id = shuttle.getId();
            String name = shuttle.getName();
            String type = shuttle.getType();
            String licenseNumber = shuttle.getLicenseNumber();
            return new ShuttleInShuttleDriveHistory(id, name, type, licenseNumber);
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class DriverInShuttleDriveHistory {

        private long id;
        private String name;
        private String phoneNumber;
        private String imagePath;

        private static DriverInShuttleDriveHistory from(Driver driver) {
            long id = driver.getId();
            String name = driver.getName();
            String phoneNumber = driver.getPhoneNumber();
            String imagePath = driver.getImagePath();
            return new DriverInShuttleDriveHistory(id, name, phoneNumber,imagePath);
        }

    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class TeacherInShuttleDriveHistory {

        private long id;
        private String name;
        private String phoneNumber;
        private String imagePath;

        private static TeacherInShuttleDriveHistory from(Teacher teacher) {
            long id = teacher.getId();
            String name = teacher.getName();
            String phoneNumber = teacher.getPhoneNumber();
            String imagePath = teacher.getImagePath();
            return new TeacherInShuttleDriveHistory(id, name, phoneNumber,imagePath);
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class LocationInShuttleDriveHistory {
        private double latitude;
        private double longitude;
        private LocalDateTime time;
        private int speed;

        private static LocationInShuttleDriveHistory from(ShuttleLocation shuttleLocation) {//주석처리 - 김도현
            double latitude = shuttleLocation.getLatitude();
            double longitude = shuttleLocation.getLongitude();
            LocalDateTime time = shuttleLocation.getTime();
            int speed = shuttleLocation.getSpeed();
            return new LocationInShuttleDriveHistory(latitude, longitude, time, speed);
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class BoardDropInfoInShuttleDriveHistory {
        private double latitude;
        private double longitude;
        private LocalDateTime time;
        private ChildInBoardDropInfoInShuttleDriveHistory child;
        public static BoardDropInfoInShuttleDriveHistory of(AcademyChild academyChild,double latitude, double longitude, LocalDateTime time) {//주석처리 - 김도현
            ChildInBoardDropInfoInShuttleDriveHistory childInBoardDropInfoInShuttleDriveHistory = ChildInBoardDropInfoInShuttleDriveHistory.from(academyChild);
            return new BoardDropInfoInShuttleDriveHistory(latitude, longitude, time, childInBoardDropInfoInShuttleDriveHistory);
        }

        @Getter
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        @AllArgsConstructor(access = AccessLevel.PROTECTED)
        public static class ChildInBoardDropInfoInShuttleDriveHistory {
            private long academyChildId;
            private String name;
            @Enumerated(value = EnumType.STRING)
            private Gender gender;
            private String imagePath;
            private static ChildInBoardDropInfoInShuttleDriveHistory from(AcademyChild academychild) {//주석처리 - 김도현
                return new ChildInBoardDropInfoInShuttleDriveHistory(academychild.getId(),academychild.getName(),academychild.getGender(),academychild.getImagePath());
            }
        }
    }






}
