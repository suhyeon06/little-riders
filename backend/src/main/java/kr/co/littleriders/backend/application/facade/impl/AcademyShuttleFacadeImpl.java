package kr.co.littleriders.backend.application.facade.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import kr.co.littleriders.backend.application.dto.response.AcademyShuttleResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.co.littleriders.backend.application.dto.request.ShuttleRegistRequest;
import kr.co.littleriders.backend.application.facade.AcademyShuttleFacade;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.shuttle.ShuttleService;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleStatus;
import kr.co.littleriders.backend.global.utils.ImageUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class AcademyShuttleFacadeImpl implements AcademyShuttleFacade {

	private final ShuttleService shuttleService;
	private final AcademyService academyService;
	private final ImageUtil imageUtil;

	@Override
	@Transactional
	public Long insertShuttle(ShuttleRegistRequest shuttleRegistRequest, Long academyId) {

		Academy academy = academyService.findById(academyId);
		Shuttle shuttle = shuttleRegistRequest.toEntity(academy);

		MultipartFile image = shuttleRegistRequest.getImage();
		if(image != null){
			String imagePath = imageUtil.saveImage(image);
			shuttle.setImagePath(imagePath);
		}

		return shuttleService.save(shuttle);
	}

	@Override
	public List<AcademyShuttleResponse> readShuttleList(Long academyId) {

		Academy academy = academyService.findById(academyId);

		List<AcademyShuttleResponse> shuttleList = shuttleService.findByAcademy(academy)
																 .stream()
																 .sorted(Comparator.comparing(shuttle -> shuttle.getStatus() == ShuttleStatus.USE ? 0 : 1))
																 .map(AcademyShuttleResponse::from)
																 .collect(Collectors.toList());



		return shuttleList;
	}

}
