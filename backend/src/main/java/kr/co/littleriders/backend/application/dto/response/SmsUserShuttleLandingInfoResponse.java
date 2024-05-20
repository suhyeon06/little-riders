package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.domain.driver.entity.Driver;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleLocation;
import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SmsUserShuttleLandingInfoResponse {


    private final PersonInSmsUserShuttleLandingInfoResponse teacher;
    private final PersonInSmsUserShuttleLandingInfoResponse driver;
    private final ShuttleInSmsUserShuttleLandingInfoResponse shuttle;
    private final List<LocationInSmsUserShuttleLandingInfoResponse> locationList;


    public static SmsUserShuttleLandingInfoResponse of(Teacher teacher, Driver driver, Shuttle shuttle, List<ShuttleLocation> shuttleLocationList) {
        PersonInSmsUserShuttleLandingInfoResponse teacherDTO = PersonInSmsUserShuttleLandingInfoResponse.from(teacher);
        PersonInSmsUserShuttleLandingInfoResponse driverDTO = PersonInSmsUserShuttleLandingInfoResponse.from(driver);
        ShuttleInSmsUserShuttleLandingInfoResponse shuttleDTO = ShuttleInSmsUserShuttleLandingInfoResponse.from(shuttle);
        List<LocationInSmsUserShuttleLandingInfoResponse> locationDTOList = LocationInSmsUserShuttleLandingInfoResponse.from(shuttleLocationList);
        return new SmsUserShuttleLandingInfoResponse(teacherDTO, driverDTO, shuttleDTO, locationDTOList);

    }


    @Getter
    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    private static class PersonInSmsUserShuttleLandingInfoResponse {
        private final String name;
        private final String image;
        private final String phoneNumber;

        public static PersonInSmsUserShuttleLandingInfoResponse from(Driver driver) {
            return new PersonInSmsUserShuttleLandingInfoResponse(driver.getName(), driver.getImagePath(), driver.getPhoneNumber());
        }

        public static PersonInSmsUserShuttleLandingInfoResponse from(Teacher teacher) {
            return new PersonInSmsUserShuttleLandingInfoResponse(teacher.getName(), teacher.getImagePath(), teacher.getPhoneNumber());

        }

    }


    @Getter
    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    private static class ShuttleInSmsUserShuttleLandingInfoResponse {

        private final String name;
        private final String type;
        private final String image;
        private final String licenseNumber;


        public static ShuttleInSmsUserShuttleLandingInfoResponse from(Shuttle shuttle) {
            return new ShuttleInSmsUserShuttleLandingInfoResponse(shuttle.getName(), shuttle.getType(), shuttle.getImagePath(), shuttle.getLicenseNumber());
        }
    }


    @Getter
    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    private static class LocationInSmsUserShuttleLandingInfoResponse {

        private final double latitude;
        private final double longitude;
        private final int speed;
        private final LocalDateTime time;

        public static List<LocationInSmsUserShuttleLandingInfoResponse> from(List<ShuttleLocation> shuttleLocationList) {
            return shuttleLocationList.stream().map(LocationInSmsUserShuttleLandingInfoResponse::from).toList();
        }

        private static LocationInSmsUserShuttleLandingInfoResponse from(ShuttleLocation shuttleLocation) {
            return new LocationInSmsUserShuttleLandingInfoResponse(shuttleLocation.getLatitude(), shuttleLocation.getLongitude(), shuttleLocation.getSpeed(), shuttleLocation.getTime());
        }
    }
}
