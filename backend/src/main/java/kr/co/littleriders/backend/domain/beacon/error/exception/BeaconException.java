package kr.co.littleriders.backend.domain.beacon.error.exception;


import kr.co.littleriders.backend.domain.academy.error.code.AcademyChildErrorCode;
import kr.co.littleriders.backend.domain.beacon.error.code.BeaconErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.Getter;

@Getter
public final class BeaconException extends LittleRidersException {

    BeaconException(BeaconErrorCode errorCode) {
        super(errorCode);
    }

    public static BeaconException from(BeaconErrorCode errorCode) {
        return new BeaconException(errorCode);
    }

}
