package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.domain.history.entity.ShuttleBoardDropHistory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SmsUserHistoryResponse {

    private final DriverInSmsUserHistoryResponse driver;
    private final TeacherInSmsUserHistoryResponse teacher;
    private final BoardDropInSmsUserHistoryResponse board;
    private final BoardDropInSmsUserHistoryResponse drop;
    private final List<LocationInSmsUserHistoryResponse> locationList;
    private final ShuttleInSmsUserHistoryResponse shuttle;



    public static SmsUserHistoryResponse from(ShuttleBoardDropHistory shuttleBoardDropHistory) {

        DriverInSmsUserHistoryResponse driverInSmsUserHistoryResponse = DriverInSmsUserHistoryResponse.from(shuttleBoardDropHistory.getDriver());
        TeacherInSmsUserHistoryResponse teacherInSmsUserHistoryResponse = TeacherInSmsUserHistoryResponse.from(shuttleBoardDropHistory.getTeacher());
        BoardDropInSmsUserHistoryResponse board = BoardDropInSmsUserHistoryResponse.of(shuttleBoardDropHistory.getChild(),shuttleBoardDropHistory.getBoard());
        BoardDropInSmsUserHistoryResponse drop = BoardDropInSmsUserHistoryResponse.of(shuttleBoardDropHistory.getChild(), shuttleBoardDropHistory.getDrop());

        List<LocationInSmsUserHistoryResponse> locationList = shuttleBoardDropHistory.getLocationList().stream().map(LocationInSmsUserHistoryResponse::from).toList();

        ShuttleInSmsUserHistoryResponse shuttleInSmsUserHistoryResponse = ShuttleInSmsUserHistoryResponse.from(shuttleBoardDropHistory.getShuttle());
        return new SmsUserHistoryResponse(driverInSmsUserHistoryResponse,teacherInSmsUserHistoryResponse,board,drop,locationList,shuttleInSmsUserHistoryResponse);
    }


    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ShuttleInSmsUserHistoryResponse {
        private String name;
        private String type;
        private String licenseNumber;

        private static ShuttleInSmsUserHistoryResponse from(ShuttleBoardDropHistory.ShuttleInShuttleBoardDropHistory shuttle) {
            String name = shuttle.getName();
            String type = shuttle.getType();
            String licenseNumber = shuttle.getLicenseNumber();
            return new ShuttleInSmsUserHistoryResponse(name, type, licenseNumber);
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class DriverInSmsUserHistoryResponse {

        private String name;
        private String phoneNumber;
        private String image;

        private static DriverInSmsUserHistoryResponse from(ShuttleBoardDropHistory.DriverInShuttleBoardDropHistory driver) {
            String name = driver.getName();
            String phoneNumber = driver.getPhoneNumber();
            String image = driver.getImage();
            return new DriverInSmsUserHistoryResponse(name, phoneNumber, image);
        }

    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class TeacherInSmsUserHistoryResponse {

        private String name;
        private String phoneNumber;
        private String image;

        private static TeacherInSmsUserHistoryResponse from(ShuttleBoardDropHistory.TeacherInShuttleBoardDropHistory teacher) {
            long id = teacher.getId();
            String name = teacher.getName();
            String phoneNumber = teacher.getPhoneNumber();
            String image = teacher.getImage();
            return new TeacherInSmsUserHistoryResponse(name, phoneNumber, image);
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class BoardDropInSmsUserHistoryResponse {

        private double latitude;
        private double longitude;
        private LocalDateTime time;
        private ChildInBoardDropInSmsUserHistoryResponse child;

        private static BoardDropInSmsUserHistoryResponse of(ShuttleBoardDropHistory.ChildInShuttleBoardDropHistory childInShuttleBoardDropHistory,ShuttleBoardDropHistory.BoardDropInShuttleBoardDropHistory shuttleBoardDropHistory) {
            double latitude = shuttleBoardDropHistory.getLatitude();
            double longitude = shuttleBoardDropHistory.getLongitude();
            LocalDateTime time = shuttleBoardDropHistory.getTime();
            ChildInBoardDropInSmsUserHistoryResponse childInBoardDropInSmsUserHistoryResponse = ChildInBoardDropInSmsUserHistoryResponse.from(childInShuttleBoardDropHistory);

            return new BoardDropInSmsUserHistoryResponse(latitude, longitude, time,childInBoardDropInSmsUserHistoryResponse);
        }

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class ChildInBoardDropInSmsUserHistoryResponse {
            private String name;
            private String image;

            private static ChildInBoardDropInSmsUserHistoryResponse from(ShuttleBoardDropHistory.ChildInShuttleBoardDropHistory childInShuttleBoardDropHistory) {
                String name = childInShuttleBoardDropHistory.getName();
                String image = childInShuttleBoardDropHistory.getImage();
                return new ChildInBoardDropInSmsUserHistoryResponse(name, image);
            }
        }

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LocationInSmsUserHistoryResponse {

        private double latitude;
        private double longitude;
        private int speed;
        private LocalDateTime time;

        private static LocationInSmsUserHistoryResponse from(ShuttleBoardDropHistory.LocationInShuttleBoardDropHistory shuttleLocation) {
            double latitude = shuttleLocation.getLatitude();
            double longitude = shuttleLocation.getLongitude();
            int speed = shuttleLocation.getSpeed();
            LocalDateTime time = shuttleLocation.getTime();
            return new LocationInSmsUserHistoryResponse(latitude, longitude, speed, time);
        }
    }


}
