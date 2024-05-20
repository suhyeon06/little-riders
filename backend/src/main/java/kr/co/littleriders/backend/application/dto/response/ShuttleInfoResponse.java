package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import lombok.Getter;

@Getter
public class ShuttleInfoResponse {

    private final String name;
    private final String licenseNumber;
    private final String type;
    private final String image;



    private ShuttleInfoResponse(String name, String licenseNumber, String type,String image) {
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.type = type;
        this.image = image;
    }

    public static ShuttleInfoResponse from(Shuttle shuttle){
        return new ShuttleInfoResponse(shuttle.getName(),shuttle.getLicenseNumber(),shuttle.getType(),shuttle.getImagePath());
    }
}
