package kr.co.littleriders.backend.domain.shuttle.service;

import kr.co.littleriders.backend.domain.shuttle.DriveUniqueKeyService;
import kr.co.littleriders.backend.domain.shuttle.entity.DriveUniqueKey;
import kr.co.littleriders.backend.domain.shuttle.error.code.DriveUniqueKeyErrorCode;
import kr.co.littleriders.backend.domain.shuttle.error.exception.DriveUniqueKeyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class DriveUniqueKeyServiceImpl implements DriveUniqueKeyService {

    private final DriveUniqueKeyRepository driveUniqueKeyRepository;

    @Override
    @Transactional
    public void save(DriveUniqueKey driveUniqueKey) {
        driveUniqueKeyRepository.save(driveUniqueKey);
    }

    @Override
    public List<DriveUniqueKey> findByShuttleId(long shuttleId) {
        return driveUniqueKeyRepository.findByShuttleId(shuttleId);
    }

    @Override
    @Transactional
    public void delete(DriveUniqueKey driveUniqueKey) {
        driveUniqueKeyRepository.delete(driveUniqueKey);
    }

    @Override
    @Transactional
    public void deleteAllByShuttleId(long shuttleId) {
        List<DriveUniqueKey> driveUniqueKeyList = driveUniqueKeyRepository.findByShuttleId(shuttleId);
        driveUniqueKeyRepository.deleteAll(driveUniqueKeyList);
    }


    @Override
    public DriveUniqueKey findByUuid(String uuid) {
        return driveUniqueKeyRepository.findById(uuid).orElseThrow(
                () -> DriveUniqueKeyException.from(DriveUniqueKeyErrorCode.NOT_FOUND));
    }

    @Override
    public DriveUniqueKey findByAcademyChildId(long academyChildId) {
        return driveUniqueKeyRepository.findByAcademyChildId(academyChildId).orElseThrow(
                () -> DriveUniqueKeyException.from(DriveUniqueKeyErrorCode.NOT_FOUND)
        );
    }


}