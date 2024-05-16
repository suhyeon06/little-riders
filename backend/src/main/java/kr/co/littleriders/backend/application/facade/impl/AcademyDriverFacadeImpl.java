package kr.co.littleriders.backend.application.facade.impl;

import java.util.*;
import java.util.stream.Collectors;

import kr.co.littleriders.backend.application.dto.response.AcademyDriverResponse;
import kr.co.littleriders.backend.application.dto.response.DriverDetailResponse;
import kr.co.littleriders.backend.domain.driver.entity.DriverStatus;
import kr.co.littleriders.backend.domain.driver.error.code.DriverErrorCode;
import kr.co.littleriders.backend.domain.driver.error.exception.DriverException;
import kr.co.littleriders.backend.global.utils.ImageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.co.littleriders.backend.application.dto.request.DriverRegistRequest;
import kr.co.littleriders.backend.application.facade.AcademyDriverFacade;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.driver.DriverService;
import kr.co.littleriders.backend.domain.driver.entity.Driver;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class AcademyDriverFacadeImpl implements AcademyDriverFacade {

	private final DriverService driverService;
	private final AcademyService academyService;
	private final ImageUtil imageUtil;

	@Override
	@Transactional
	public Long insertDriver(DriverRegistRequest driverRegistRequest, Long academyId) {

		Academy academy = academyService.findById(academyId);
		Driver driver = driverRegistRequest.toEntity(academy);

		MultipartFile image = driverRegistRequest.getImage();
		if(image != null){
			String imagePath = imageUtil.saveImage(image);
			driver.setImagePath(imagePath);
		}

		return driverService.save(driver);
	}

	@Override
	public List<AcademyDriverResponse> readDriverList(Long academyId) {

		Academy academy = academyService.findById(academyId);
		return driverService.findByAcademy(academy)
							.stream()
							.sorted(Comparator.comparing(driver -> driver.getStatus() == DriverStatus.WORK ? 0 : 1))
							.map(AcademyDriverResponse::from)
							.collect(Collectors.toList());

	}

	@Override
	public DriverDetailResponse readDriverDetail(Long academyId, Long driverId) {

		Academy academy = academyService.findById(academyId);
		Driver driver = driverService.findById(driverId);
		if (!driver.equalsAcademy(academy)) {
			throw DriverException.from(DriverErrorCode.ILLEGAL_ACCESS);
		}

		return DriverDetailResponse.from(driver);
	}

}
