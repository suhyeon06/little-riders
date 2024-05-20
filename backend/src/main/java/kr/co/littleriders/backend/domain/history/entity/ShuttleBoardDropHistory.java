package kr.co.littleriders.backend.domain.history.entity;


import jakarta.persistence.Id;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.driver.entity.Driver;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleBoard;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleDrop;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleLocation;
import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;


@Document("board_drop_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ShuttleBoardDropHistory {

    @Id
    private String documentId;

    private String uuid;
    private ShuttleInShuttleBoardDropHistory shuttle;
    private DriverInShuttleBoardDropHistory driver;
    private TeacherInShuttleBoardDropHistory teacher;
    private BoardDropInShuttleBoardDropHistory board;
    private BoardDropInShuttleBoardDropHistory drop;
    private ChildInShuttleBoardDropHistory child;
    private List<LocationInShuttleBoardDropHistory> locationList;

    private ShuttleBoardDropHistory(String uuid,
                                    ShuttleInShuttleBoardDropHistory shuttleInBoardDropHistory,
                                    DriverInShuttleBoardDropHistory driverInBoardDropHistory,
                                    TeacherInShuttleBoardDropHistory teacherInBoardDropHistory,
                                    BoardDropInShuttleBoardDropHistory boardInBoardDropHistory,
                                    BoardDropInShuttleBoardDropHistory dropInBoardDropHistory,
                                    List<LocationInShuttleBoardDropHistory> locationList,
                                    ChildInShuttleBoardDropHistory child) {
        this.uuid = uuid;
        this.shuttle = shuttleInBoardDropHistory;
        this.driver = driverInBoardDropHistory;
        this.teacher = teacherInBoardDropHistory;
        this.board = boardInBoardDropHistory;
        this.drop = dropInBoardDropHistory;
        this.locationList = locationList;
        this.child = child;

    }

    public static ShuttleBoardDropHistory of(String uuid, Shuttle shuttle, Driver driver, Teacher teacher, ShuttleBoard shuttleBoard, ShuttleDrop shuttleDrop, List<ShuttleLocation> shuttleLocationList,AcademyChild academyChild) {
        ShuttleInShuttleBoardDropHistory shuttleInBoardDropHistory = ShuttleInShuttleBoardDropHistory.from(shuttle);
        DriverInShuttleBoardDropHistory driverInBoardDropHistory = DriverInShuttleBoardDropHistory.from(driver);
        TeacherInShuttleBoardDropHistory teacherInBoardDropHistory = TeacherInShuttleBoardDropHistory.from(teacher);
        BoardDropInShuttleBoardDropHistory boardInBoardDropHistory = BoardDropInShuttleBoardDropHistory.from(shuttleBoard);
        BoardDropInShuttleBoardDropHistory dropInBoardDropHistory = BoardDropInShuttleBoardDropHistory.from(shuttleDrop);

        List<LocationInShuttleBoardDropHistory> locationInBoardDropHistoryList = shuttleLocationList.stream().map(LocationInShuttleBoardDropHistory::from)
                .toList();


        ChildInShuttleBoardDropHistory childInBoarDropHistory = ChildInShuttleBoardDropHistory.from(academyChild);
        return new ShuttleBoardDropHistory(uuid, shuttleInBoardDropHistory, driverInBoardDropHistory, teacherInBoardDropHistory, boardInBoardDropHistory, dropInBoardDropHistory,locationInBoardDropHistoryList,childInBoarDropHistory);
    }


    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ShuttleInShuttleBoardDropHistory {
        private long id;
        private String name;
        private String type;
        private String licenseNumber;

        private static ShuttleInShuttleBoardDropHistory from(Shuttle shuttle) {
            long id = shuttle.getId();
            String name = shuttle.getName();
            String type = shuttle.getType();
            String licenseNumber = shuttle.getLicenseNumber();
            return new ShuttleBoardDropHistory.ShuttleInShuttleBoardDropHistory(id, name, type, licenseNumber);
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class DriverInShuttleBoardDropHistory {

        private long id;
        private String name;
        private String phoneNumber;
        private String image;

        private static DriverInShuttleBoardDropHistory from(Driver driver) {
            long id = driver.getId();
            String name = driver.getName();
            String phoneNumber = driver.getPhoneNumber();
            String image = driver.getImagePath();
            return new DriverInShuttleBoardDropHistory(id, name, phoneNumber,image);
        }

    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class TeacherInShuttleBoardDropHistory {

        private long id;
        private String name;
        private String phoneNumber;
        private String image;

        private static TeacherInShuttleBoardDropHistory from(Teacher teacher) {
            long id = teacher.getId();
            String name = teacher.getName();
            String phoneNumber = teacher.getPhoneNumber();
            String image = teacher.getImagePath();
            return new TeacherInShuttleBoardDropHistory(id, name, phoneNumber,image);
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class BoardDropInShuttleBoardDropHistory {

        private double latitude;
        private double longitude;
        private LocalDateTime time;

        private static BoardDropInShuttleBoardDropHistory from(ShuttleBoard shuttleBoard) {
            double latitude = shuttleBoard.getLatitude();
            double longitude = shuttleBoard.getLongitude();
            LocalDateTime time = shuttleBoard.getTime();

            return new BoardDropInShuttleBoardDropHistory(latitude, longitude, time);
        }

        private static BoardDropInShuttleBoardDropHistory from(ShuttleDrop shuttleDrop) {
            double latitude = shuttleDrop.getLatitude();
            double longitude = shuttleDrop.getLongitude();
            LocalDateTime time = shuttleDrop.getTime();

            return new BoardDropInShuttleBoardDropHistory(latitude, longitude, time);
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LocationInShuttleBoardDropHistory {

        private double latitude;
        private double longitude;
        private int speed;
        private LocalDateTime time;

        private static LocationInShuttleBoardDropHistory from(ShuttleLocation shuttleLocation) {
            double latitude = shuttleLocation.getLatitude();
            double longitude = shuttleLocation.getLongitude();
            int speed = shuttleLocation.getSpeed();
            LocalDateTime time = shuttleLocation.getTime();
            return new LocationInShuttleBoardDropHistory(latitude, longitude, speed, time);
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChildInShuttleBoardDropHistory{
        private String name;
        private String image;

        private static ChildInShuttleBoardDropHistory from(AcademyChild academyChild){
            String name = academyChild.getName();
            String image = academyChild.getImagePath();
            return new ChildInShuttleBoardDropHistory(name,image);
        }
    }
}
