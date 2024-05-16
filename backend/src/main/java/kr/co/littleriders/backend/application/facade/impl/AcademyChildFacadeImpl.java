package kr.co.littleriders.backend.application.facade.impl;

import kr.co.littleriders.backend.application.dto.request.CreateAcademyChildRequest;
import kr.co.littleriders.backend.application.dto.request.UpdateAcademyChildRequest;
import kr.co.littleriders.backend.application.dto.response.AcademyChildDetailResponse;
import kr.co.littleriders.backend.application.dto.response.AcademyChildResponse;
import kr.co.littleriders.backend.application.dto.response.BeaconResponse;
import kr.co.littleriders.backend.application.facade.AcademyChildFacade;
import kr.co.littleriders.backend.domain.academy.AcademyChildService;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChildStatus;
import kr.co.littleriders.backend.domain.academy.error.code.AcademyChildErrorCode;
import kr.co.littleriders.backend.domain.academy.error.exception.AcademyChildException;
import kr.co.littleriders.backend.domain.beacon.BeaconServcie;
import kr.co.littleriders.backend.domain.beacon.entity.Beacon;
import kr.co.littleriders.backend.global.utils.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AcademyChildFacadeImpl implements AcademyChildFacade {

    private final AcademyService academyService;
    private final AcademyChildService academyChildService;
    private final ImageUtil imageUtil;
    private final BeaconServcie beaconServcie;

    @Override
    public List<AcademyChildResponse> getAcademyChildListByAcademyId(Long academyId) {

        Academy academy = academyService.findById(academyId);
        return academy.getAcademyChildList()
                .stream()
                .sorted(Comparator.comparing(academyChild ->
                    academyChild.getStatus() == AcademyChildStatus.ATTENDING ? 0 : 1
                ))
                .map(AcademyChildResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public AcademyChildDetailResponse getAcademyChildDetail(Long academyId, Long academyChildId) {

       Academy academy = academyService.findById(academyId);
       AcademyChild academyChild = academyChildService.findById(academyChildId);
       if (!academyChild.equalsAcademy(academy)) {
           throw AcademyChildException.from(AcademyChildErrorCode.ILLEGAL_ACCESS);
       }

       return AcademyChildDetailResponse.from(academyChild);
    }

    @Override
    @Transactional
    public Long insertAcademyChild(Long academyId, CreateAcademyChildRequest createAcademyChildRequest) {

        Academy academy = academyService.findById(academyId);
        MultipartFile image = createAcademyChildRequest.getImage();
        String imagePath = imageUtil.saveImage(image);
        Long beaconId = createAcademyChildRequest.getBeaconId();
        Long insertChildId;
        if (beaconId != null) {
            Beacon beacon = beaconServcie.findById(beaconId);
            AcademyChild academyChild = createAcademyChildRequest.toAcademyChild(academy, beacon, imagePath, AcademyChildStatus.ATTENDING);
            insertChildId = academyChildService.save(academyChild);
            beacon.updateAcademyChild(academyChild);
            beaconServcie.save(beacon);
        } else {
            AcademyChild academyChild = createAcademyChildRequest.toAcademyChild(academy, null, imagePath, AcademyChildStatus.ATTENDING);
            insertChildId = academyChildService.save(academyChild);
        }


        return insertChildId;
    }

    @Override
    public List<BeaconResponse> getBeaconList(Long academyId) {

        Academy academy = academyService.findById(academyId);
        return beaconServcie.findByAcademy(academy).stream()
                                                   .filter(beacon -> beacon.getAcademyChild() == null)
                                                   .map(BeaconResponse::from)
                                                   .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Long updateAcademyChildStatus(Long academyId, Long academyChildId, String status) {

        Academy academy = academyService.findById(academyId);
        AcademyChild academyChild = academyChildService.findById(academyChildId);
        if (!academyChild.equalsAcademy(academy)) {
            throw AcademyChildException.from(AcademyChildErrorCode.ILLEGAL_ACCESS);
        }

        AcademyChildStatus childStatus = AcademyChildStatus.valueOf(status);
        if (academyChild.isAttending() && !childStatus.equals(AcademyChildStatus.ATTENDING) && academyChild.getBeacon() != null) {
            Beacon beacon = academyChild.getBeacon();
            beacon.updateAcademyChild(null);
            beaconServcie.save(beacon);
            academyChild.detachBeacon();
        }

        academyChild.updateStatus(childStatus);
        academyChildService.save(academyChild);

        return academyChild.getId();
    }

    //TODO - HOTFIX-이윤지 수정 필요 - status 만 업데이트 할게 아닌 다른 정보도 업데이트 해야함 Attending 상관없음 (김도현)
    @Override
    @Transactional
    public Long updateAcademyChild(Long academyId, Long academyChildId, UpdateAcademyChildRequest updateAcademyChildRequest) {



        return null;
    }

}
