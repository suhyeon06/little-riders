package kr.co.littleriders.backend.application.facade;

import java.util.Map;

public interface ContentFacade {
	Map<String, Object> readImage(String uuid);
}
