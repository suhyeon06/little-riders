package kr.co.littleriders.backend.global.error.code;

import org.springframework.http.HttpStatus;

public enum ImageErrorCode implements LittleRidersErrorCode {

	NOT_FOUND(HttpStatus.NOT_FOUND, "001", "파일을 찾을 수 없습니다."),
	ILLEGAL_EXTENSION(HttpStatus.BAD_REQUEST, "002", "지원하는 확장자가 아닙니다."),
	FILE_NOT_READABLE(HttpStatus.INTERNAL_SERVER_ERROR, "003", "파일을 읽는 중 오류가 발생했습니다.");

	ImageErrorCode (HttpStatus status, String code, String message) {
		this.status = status;
		this.code = "IMAGE_" + code;
		this.message = message;
	}

	private final HttpStatus status;
	private final String code;
	private final String message;


	@Override
	public HttpStatus getStatus() {
		return status;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
