package kr.co.littleriders.backend.application.controller;

import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.littleriders.backend.application.facade.ContentFacade;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/content")
@RequiredArgsConstructor
public class ContentController {

	private final ContentFacade contentFacade;

	@GetMapping("/{uuid}")
	public ResponseEntity<Resource> getImage(@PathVariable(value = "uuid") String uuid) {

		Map<String, Object> image = contentFacade.readImage(uuid);

		Resource imageResource = (Resource) image.get("resource");
		MediaType mediaType = (MediaType) image.get("mediaType");

		HttpHeaders headers = new HttpHeaders();
		if (mediaType != null) {
			headers.setContentType(mediaType);
		} else {
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		}

		return ResponseEntity.ok().headers(headers).body(imageResource);
	}
}
