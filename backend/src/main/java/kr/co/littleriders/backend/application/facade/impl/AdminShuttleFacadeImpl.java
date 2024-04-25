package kr.co.littleriders.backend.application.facade.impl;

import org.springframework.stereotype.Service;

import kr.co.littleriders.backend.application.dto.request.ShuttleRegistRequest;
import kr.co.littleriders.backend.application.facade.AdminShuttleFacade;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.shuttle.ShuttleService;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleStatus;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class AdminShuttleFacadeImpl implements AdminShuttleFacade {

	private final ShuttleService shuttleService;

	@Override
	public Long insertShuttle(ShuttleRegistRequest shuttleRegistRequest, Academy academy) {
		Shuttle shuttle;

		if (shuttleRegistRequest.getImage() != null) {
			// 이미지 저장
			String imagePath = null;

			shuttle = Shuttle.of(shuttleRegistRequest, academy, ShuttleStatus.USE, imagePath);
		} else {
			shuttle = Shuttle.of(shuttleRegistRequest, academy);
		}

		return shuttleService.save(shuttle);
	}
}
