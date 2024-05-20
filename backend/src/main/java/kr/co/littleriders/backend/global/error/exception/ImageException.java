package kr.co.littleriders.backend.global.error.exception;

import kr.co.littleriders.backend.global.error.code.AuthErrorCode;
import kr.co.littleriders.backend.global.error.code.ImageErrorCode;
import lombok.Getter;

@Getter
public final class ImageException extends LittleRidersException {

    ImageException(ImageErrorCode errorCode) {
        super(errorCode);
    }

    public static ImageException from(ImageErrorCode errorCode) {
        return new ImageException(errorCode);
    }

}
