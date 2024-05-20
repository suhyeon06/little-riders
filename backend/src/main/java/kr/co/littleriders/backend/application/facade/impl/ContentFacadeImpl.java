package kr.co.littleriders.backend.application.facade.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import kr.co.littleriders.backend.application.facade.ContentFacade;
import kr.co.littleriders.backend.global.utils.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContentFacadeImpl implements ContentFacade {

	private final ImageUtil imageUtil;

	@Override
	public Map<String, Object> readImage(String uuid) {

		return imageUtil.getImageByUUID(uuid);
	}
}
