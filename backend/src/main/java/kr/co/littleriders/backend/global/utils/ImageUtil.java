package kr.co.littleriders.backend.global.utils;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import kr.co.littleriders.backend.global.error.code.ImageErrorCode;
import kr.co.littleriders.backend.global.error.exception.ImageException;

@Component

public class ImageUtil {
	private final String BASE_PATH;
	// TODO-이윤지-default image 설정 필요
	private static final String defaultImage = "default.jpg";

	public ImageUtil(@Value("${spring.resource.directory}") String basePath) {
		BASE_PATH = basePath;
	}

	public String saveImage(MultipartFile file)  {

		if (file == null || file.isEmpty()) {
			return defaultImage;
		}

		String originName = file.getOriginalFilename();
		String ext = getExtension(StringUtils.getFilenameExtension(originName));
		String generatedName = UUID.randomUUID() + ext;

		try {
			byte[] bytes = new byte[0];
			bytes = file.getBytes();
			Path path = Paths.get(BASE_PATH, generatedName);
			Files.write(path, bytes);

		} catch (IOException e) {
			throw ImageException.from(ImageErrorCode.FILE_NOT_READABLE);
		}

		return generatedName;
	}

	private String getExtension(String extension) {
		switch (extension.toLowerCase()) {
			case "jpeg":
			case "jpg":
			case "png":
				return "." + extension;
			default:
				throw ImageException.from(ImageErrorCode.ILLEGAL_EXTENSION);
		}
	}

	private MediaType getMediaType(String imagePath) {

		String extension = null;
		int dotIndex = imagePath.lastIndexOf('.');
		if (dotIndex > 0 && dotIndex < imagePath.length() - 1) {
			extension = imagePath.substring(dotIndex + 1);
		}

		switch (extension.toLowerCase()) {
			case "jpeg", "jpg":
				return MediaType.IMAGE_JPEG;
			case "png":
				return MediaType.IMAGE_PNG;
			default:
				throw ImageException.from(ImageErrorCode.ILLEGAL_EXTENSION);
		}
	}

	public Map<String, Object> getImage(String imagePath) {
		Map<String, Object> result = new HashMap<>();
		Path imageFilePath = Paths.get(imagePath);

		if (!Files.exists(imageFilePath)) {
			throw ImageException.from(ImageErrorCode.NOT_FOUND);
		}

		Resource imageResource = new FileSystemResource(imageFilePath);
		MediaType mediaType = getMediaType(imagePath);

		result.put("resource", imageResource);
		result.put("mediaType", mediaType);

		return result;
    }

	public Map<String, Object> getImageByUUID(String uuid) {

		Path imagePath = Paths.get(BASE_PATH, uuid);
		return getImage(imagePath.toString());
	}
}
